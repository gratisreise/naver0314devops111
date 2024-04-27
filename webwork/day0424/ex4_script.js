$(function(){
    //벽지 이미지 클릭시 이벤트
    $("ul.list img").click(function(){
        //1.현재 클릭한 이미지의 부모태그인 li 에 active 추가
        $(this).parent().addClass("active");
        //2.그 이전에 추가된 active 제거
        $(this).parent().siblings().removeClass("active");
        //3.클릭한 이미지의 title얻기
        let title=$(this).attr("title");
        //4.위의 title 을 h1.title에 적용
        $("h1.title").text(title);
        //5.클릭한 이미지의 src얻기
        let imgSrc=$(this).attr("src");
        //6.위의 src를 div.wall 에 넣는데 backgroun-image 로 넣는다
        //(url 로 넣기)
        let s=`url("${imgSrc}")`;
        $("div.wall").css("background-image",s);

    });
});