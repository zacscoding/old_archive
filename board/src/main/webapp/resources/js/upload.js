//이미지 타입인지 체크
function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i;	
	return fileName.match(pattern);
}

//fuleName = '/2017/06/05
function getFileInfo(fullName,type) {
	// 경로, UUID 제외 파일 이름
	var fileName;
	// URI + ( 썸네일 or 파일 이미지 )
	var imgsrc;
	// 
	var getLink;
	var fileLink;
	
	if( checkImageType(fullName) ) {		
		imgsrc = "/displayAttach?type=temp&fileName="+fullName;
		fileLink = fullName.substr(14);
		var front = fullName.substr(0,12); // /2017/00/00
		var end = fullName.substr(14);
		getLink = "/displayAttach?type=temp&fileName="+front+end;		
	} else {
		imgsrc = "/resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/displayAttach?type=temp&fileName=" + fullName;
	}
	fileName = fileLink.substr( fileLink.indexOf("_") +1 );
	return {fileName:fileName,imgsrc:imgsrc,getLink:getLink,fullName:fullName};	
}