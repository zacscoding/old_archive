function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}	
function getFileInfo(fullName) {
	var fileName; //경로나 UUID가 제외된 파일의 이름
	var imgsrc; //화면상에 보여주는 썸네일 or 파일 이미지의 경로
	var getLink; //화면에서 원본 파일을 볼 수 있는 링크
	var fileLink;
	if(checkImageType(fullName)) {
		imgsrc = "/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);		
		var front = fullName.substr(0,12); // /2017/00/00
		var end = fullName.substr(14);		
		getLink = "displayFile?fileName="+front+end;			
	} else {
		imgsrc="/resources/dist/img/file.png";	//파일 이미지
		fileLink = fullName.substr(12);
		getLink = "/displayFile?fileName="+fullName;			
	}	
	fileName = fileLink.substr(fileLink.indexOf("_")+1);	
	return {fileName:fileName,imgsrc:imgsrc,getLink:getLink,fullName:fullName};		
}