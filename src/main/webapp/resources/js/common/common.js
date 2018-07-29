function changeVerityCode(img){
	img.src = "../Kaptcha?"+Math.floor(Math.random()*1000);
}