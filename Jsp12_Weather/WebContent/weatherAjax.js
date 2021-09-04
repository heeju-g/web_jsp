$(function() {
	
	$("#weaView").click(
			function() {
				var url = "weatherOpen";
				//id address중 선택된 option의 value가져옴 ex)1168064000
				var code = $("#address option:selected").val();
				$.ajax({
					type : "GET",
					// url(요청보내질 곳) = weatherOpen?code=1168064000
					url : url + "?code=" + code,
					//text타입으로 받을 것
					dataType : "text",
					success : function(data) {
						
						var temp = $.trim(data);
						//문자열로 받은 걸 json객체로 변환
						var obj = JSON.parse(temp);
						
						//id가 ?인 요소의 값을 obj.?로 설정한다. ~.key에 해당하는 value값 나올 것
						$("#pubDate").val(obj.pubDate);
						$("#temp").val(obj.temp);
						$("#x").val(obj.x);
						$("#y").val(obj.y);
						$("#reh").val(obj.reh);
						$("#pop").val(obj.pop);
						$("#wfKor").val(obj.wfKor);

						
						var weather_condition = obj.wfKor;
						//obj.wfkor에 따라 해당 이미지 설정. src속성의 값에 이미지경로 줌
						if (weather_condition == "맑음"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/sun.png");
						}else if (weather_condition == "비"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/rain.png");
						}else if (weather_condition == "눈"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/snow.png");
						}else if (weather_condition == "흐림"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/cloud.png");
						}else if (weather_condition == "구름 조금"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/cloud_sun.png");
						}else{
							$("#weather_img").attr("src","/Jsp12_Weather/image/etc.png");
						}
					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			});
});
