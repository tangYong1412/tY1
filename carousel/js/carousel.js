window.onload=function(){
        var imgs_div=document.getElementById("imgs");
        var nav_div=document.getElementById("tab-btn");
        //获取到图片轮播的ul对象数组
        var imgsUl=imgs_div.getElementsByTagName("ul")[0];
        //获取到远点的ul对象数组
        var nav=nav_div.getElementsByTagName("ul")[0];
        //上一个
        var prious=document.getElementById("tab-l");
        //下一个
        var next =document.getElementById("tab-r");
        var timer;
        var animTimer;//
        var index=1;
        prious.onclick=function(){
            initImgs(index);
            index-=1;
            if(index<1){
                index=4;
            }
            animate(400);
            btnShow(index);
        }          
        next.onclick=function(){
            initImgs(index);
            index+=1;
            if(index>4){
                index=1;
            }
            animate(-400);
            btnShow(index);
        }
        function initImgs(cur_index){
            clearInterval(timer);
            clearInterval(animTimer);
            var off=cur_index*400;
            imgsUl.style.left=-off+"px";
        }
        function animate(offset){
            var newLeft=parseInt(imgsUl.offsetLeft)+offset;
            // imgsUl.style.left=newLeft;
            // console.log("定时器外面:此时offsetLeft"+imgsUl.offsetLeft+">>newLeft:"+newLeft);
            if(newLeft>-400){
            	//如果上一张是最后一张图片
                // imgsUl.style.left=-2000+"px";
                animater(-2000); 
            }else if(newLeft<-2000){
            	//如果是第一张到第四张
                // imgsUl.style.left=-400+"px";
                animater(-400); 
            }else{
            	//如果最后一张
                animater(newLeft);
            }
        }
        function animater(offset){
            clearInterval(animTimer);//停止自动执行
            animTimer=setInterval(function(){
                imgsUl.style.left=imgsUl.offsetLeft+(offset-imgsUl.offsetLeft)/10 + "px";//每个20毫秒计算当前图片的位置
                if(imgsUl.offsetLeft-offset<10&&imgsUl.offsetLeft-offset>-10){//如果偏移量已经等于指定好的偏移量，停止自动执行
                    imgsUl.style.left=offset+"px";
                    clearInterval(animTimer);
                    //开启定时轮播
                    play();         
                }
            },20);
        }
        function btnShow(cur_index){//展示按钮轮播
            for(var i=0;i<nav.children.length;i++){
                nav.children[i].children[0].className="hidden";//将所有按钮hidden
            }
            nav.children[cur_index-1].children[0].className="current";//将与图片对应的按钮设置为current
        }
        for(var i=0;i<nav.children.length;i++){
            nav.children[i].index=i;
            var sd=nav.children[i].index;
            nav.children[i].onmouseover=function(){
                index=this.index+1;
                initImgs(this.index+1);
                btnShow(this.index+1);
            }//鼠标在图标上方则的调用图片、按钮展示
            nav.children[i].onmouseout=function(){
                play();
            }//鼠标移出开始自动轮播
        }
        function play(){
            timer=setInterval(function(){
                next.onclick();
            },2000);
        }//每隔2秒轮播
        play();
    }