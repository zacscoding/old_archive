package com.faceontalk.service.feed;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;
import com.faceontalk.persistence.feed.FeedDAO;
import com.faceontalk.persistence.feed.ReplyDAO;
import com.faceontalk.util.HashTagHelper;

/*
 * 컨트롤러에서 많은 로직들 옮기면서 method를 내부에서만 쓸 수 있음. 내부에서만 쓰면 바꾸기 
 */
@Service
public class FeedServiceImpl implements FeedService {	
	private final int REPLY_LIMIT_SIZE = 3;
	@Inject
	private FeedDAO feedDAO;
	@Inject
	private ReplyDAO replyDAO;
	
	/**		feed	*/
	
	/*	Read	*/		
	//read login user, followers
	@Override
	public List<FeedVO> listFollowersFeeds(Criteria cri, Integer user_no) throws Exception {
		List<FeedVO> feedList = feedDAO.listFollowersFeeds(cri, user_no); 
		//get reply list at first 
		for(FeedVO vo : feedList) {
			vo.setReplyList(replyDAO.list(vo.getFeed_no(), 1, REPLY_LIMIT_SIZE ));
		}
		return feedList;
	}	
	@Override
	public int listFollowersFeedCount(Integer user_no) throws Exception {
		return feedDAO.listFollowersFeedCount(user_no);
	}
	
	//read only users feed
	@Override
	public List<FeedVO> listUsersFeedPics(Integer user_no) throws Exception {
		List<FeedVO> feedList = feedDAO.listUsersFeedPics(user_no);
		return feedList;
	}

	//read search by tag_id
	@Override
	public List<FeedVO> listFeedsByTag(Criteria cri,Integer tag_id) throws Exception {
		return feedDAO.listFeedsByTag(cri,tag_id);
	}	
	@Override
	public int listCountsByTagCount(Integer tag_id) throws Exception {
		return feedDAO.listCountsByTagCount(tag_id);
	}	
	
	//read all feed
	@Override
	public List<FeedVO> listAllFeeds(Criteria cri) throws Exception {
		return feedDAO.listAllFeeds(cri);
	}	
	@Override
	public int listAllFeedCount() throws Exception {
		return feedDAO.listAllFeedCount();
	}
	
		
	/*	Create	*/
	/***/
	@Transactional
	@Override
	public void register(FeedVO vo) throws Exception {
		feedDAO.register(vo);
		//sol2) --- > 임시 (last_insert_id() 함수에 따라 비효율)
		List<String> hashTags = HashTagHelper.getAllHashTags(vo.getContent());
		if (!hashTags.isEmpty()) { // exist hash tag			
			int feed_no = feedDAO.getLastInsertedFeedNum();
			for (String tag_name : hashTags) {
				registFeedAndTag(feed_no, tag_name);
			}
		}			
//		//sol1) ---> 이걸로 다시 하기
//		if(!hashTags.isEmpty()) { //exist hash tag
//			//새로 삽입된 피드 가져오기(필요한건 feed_no)
//			vo = feedService.getLastInserted();
//			for(String tag_name : hashTags) {
//				logger.info("tag_name : "+tag_name);
//				//search hashtag 
//				HashTagVO tag = feedService.selectTagByName(tag_name);
//				Integer tag_id = null;
//				if(tag == null) { //not exist					
//					feedService.registerTags(tag_name);	
//					tag_id = -1;
//				} else { //exist
//					tag_id = tag.getTag_id();
//				}			
//				logger.info("vo : "+vo.getFeed_no() + "tag_id : "+tag_id);				
//				feedService.registerRelation(vo.getFeed_no(),tag_id);
//			}
//		}	
	}
	
	@Override
	public FeedVO getLastInsertedFeed() throws Exception {		
		return feedDAO.getLastInsertedFeed();
	}
	

	@Override
	public FeedVO selectFeedByNum(Integer feed_no) throws Exception {
		return feedDAO.selectByFeedNo(feed_no);
	}
	
	@Transactional
	@Override
	public void modify(FeedVO vo) throws Exception {		
		/*
		 * logic
		//1. check for modified hash tags
		// 1) 기존 저장된 해시 태그들을 가져옴
		// 2) 새로 변경된 내용의 해시 태그들을 가져옴
		// 3) 기존 vs 새로운 태그들 비교 
		//		==> sol1) 기존 HashMap, 새로운 태그 list => HashMap.contains(새로운태그) ????
		//		==> sol2) 
		// 4-1) 태그 그대로면 continue
		// 4-2) 태그 변동 되었으면 
		// ==> 기존 tag,feed relation 삭제 .
		// ==> 새로운 태그가 tbl_tab에 존재하지 않으면 regist tag
		// ==> regist relation...
		 */		
		//tags
		
		Map<String,HashTagVO> prevTagsMap = this.getTagsMap(vo.getFeed_no());	
		List<String> modifiedTagsList = HashTagHelper.getAllHashTags(vo.getContent());
		List<String> newTagsList = new LinkedList<>();		
		for(int i=0;i<modifiedTagsList.size();i++) {
			String tag_name = modifiedTagsList.get(i);
			if(prevTagsMap.remove(tag_name) ==null) {
				newTagsList.add(tag_name);				
			}
		}		
		//새로운 관계 생성 == newTagsList
		for(String tag_name : newTagsList) {
			registFeedAndTag(vo.getFeed_no(),tag_name);
		}		
		//기존 관계 삭제 == prevTagsMap
		Iterator<String> keyItr = prevTagsMap.keySet().iterator();
		while(keyItr.hasNext()) {
			HashTagVO tag = prevTagsMap.get(keyItr.next());
			removeRelation(vo.getFeed_no(), tag.getTag_id());
		}		
		//2.content		
		feedDAO.update(vo);
		//3. check for modified file image		
		
	}	

	@Override
	public int getLastInsertedFeedNum() throws Exception {
		return feedDAO.getLastInsertedFeedNum();
	}
	
	@Transactional
	@Override
	public void remove(Integer feed_no) throws Exception {
		feedDAO.remove(feed_no);
		feedDAO.removeRelation(feed_no,null);
		replyDAO.removeAll(feed_no);
	}
	
	@Override
	public void modifyReplyCount(Integer feed_no, boolean isIncrease) throws Exception {
		feedDAO.modifyReplyCount(feed_no,isIncrease);	
	}
	
	
	/////////////////////////////
	//tag	
	@Override
	public void registerTags(String tag_name) throws Exception {
		feedDAO.registerTag(tag_name);		
	}

	@Override
	public HashTagVO selectTagByName(String tag_name) throws Exception {
		return feedDAO.selectTagByName(tag_name);
	}

	@Override
	public HashTagVO getLastInsertedTag() throws Exception {		
		return feedDAO.getLastInsertedTag();
	}
	
	/////////////////////////////
	//relation with feed and tag	
	@Override
	public void registerRelation(Integer feed_no, Integer tag_id) throws Exception {
		feedDAO.registerRelation(feed_no, tag_id);
	}

	@Override
	public Map<String,HashTagVO> getTagsMap(Integer feed_no) throws Exception {
		return feedDAO.selectTagsByFeedNum(feed_no);
	}

	@Override
	public void removeRelation(Integer feed_no, Integer tag_id) throws Exception {
		feedDAO.removeRelation(feed_no, tag_id);
	}
	
	////////////////////////////
	//private
	private void registFeedAndTag(Integer feed_no,String tag_name) throws Exception {
		HashTagVO tag = selectTagByName(tag_name);				
		if(tag == null) {
			registerTags(tag_name);
			tag = getLastInsertedTag();
		}				
		registerRelation(feed_no,tag.getTag_id());
	}
	
	
}
