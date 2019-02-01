package com.faceontalk.domain.member;

public class FollowVO {
	private Integer follower;
	private Integer following;
	
	
	public Integer getFollower() {
		return follower;
	}
	public void setFollower(Integer follower) {
		this.follower = follower;
	}
	public Integer getFollowing() {
		return following;
	}
	public void setFollowing(Integer following) {
		this.following = following;
	}
	@Override
	public String toString() {
		return "FollowVO [follower=" + follower + ", following=" + following + "]";
	}
}
