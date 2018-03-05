define(function(){
	var test = (function(){
		var fenye ={
				//按钮响应时的下一个页面
				Current:1,
				//li数
				LisLen:0,
				//页数
				acount:0,
				init:function(amount,data,id,num,fn){//分别为总数 每页个数 插入到某处的ID 显示第几页 按钮响应函数
				num = Number(num);
				this.fn = fn;
				//创建DOM
				this.acount = parseInt((amount%data)==0?(amount/data):(amount/data+1));
				this.LisLen = (this.acount > 9) ? 9 :this.acount ;
				this.lastPage = (num > this.acount) ? this.acount :((num < 1 )?1:num);
				var $div = $("<div id='inner'></div>");
				var $ul = $("<ul class='items'></ul>");
				$div.append($ul);
				var $pre = "<li class='item pre'><a href='#'><span><< Pre</span></a></li>";
				var $next = "<li class='item next'><a href='#'><span>Next >></span></a></li>";
				var $text="";
			    for(var i =0;i<this.LisLen;i++){
			    	$text += "<li class='item page'><a href='#'><span></span></a></li>" ;
			    }
				$ul.append($pre).append($text).append($next);
				$id = $("#"+id);
				$id.append($div);
				this.pageLis = $div.find(".items .page");
				this.change(this.lastPage);
				this.control();
			},
			    eventDefault:function(event){
			       event.preventDefault();
				   return false;
			    },
			    //判断是否为首尾页 从而禁止前一页和下一页的点击事件
				isLast:function(){
					var $current = $(".current a span").html();
					//最后一页
					if($current == this.acount)
					{
						$(".items .next").addClass('hide');
					}else{
						$(".items .next").removeClass('hide');
					}
					//第一页
					if($current == 1)
					{
						$(".items .pre").addClass('hide');
				    }else{
				    	$(".items .pre").removeClass('hide');
				    }
				},
				//
				change:function(pageNum){
					pageNum = Number(pageNum);
					//渲染到第几个li
					var index = 0;
					//去除样式
			        $('#inner .items .current').removeClass('current');
			        $('#inner .items .not').removeClass('not');
			        $('#inner .items .hide').removeClass('hide');
				    //若小于四 则按顺序创建页数1,2,3等
					if(pageNum<=4){
						//console.log(this.pageLis.eq(this.Current))
						for(var i=0;i<pageNum;i++){
						if(i+1==pageNum){
					    	this.pageLis.eq(i).addClass('current');
					    }
						this.pageLis.eq(i).find("a span").html(i+1);
						index = i+1;
					}
				    //否则 先创建1,2，再创建... 再创建当前页数
					}else{
						for(var i=0;i<2;i++){
							this.pageLis.eq(i).find("a span").html(i+1);
						}
						this.pageLis.eq(2).addClass('not').find("a span").html("...");
						this.pageLis.eq(3).addClass('current').find("a span").html(pageNum);
						index = 4;
					}

					//若+2大于总页数 则按顺序显示至总页数
					if(pageNum+2>=this.acount){
						for(var i=pageNum,j=0;i<this.acount;i++){
						    this.pageLis.eq(index++).find("a span").html(pageNum+j+1);
							j++;
						};
					}else{
					//否则 先创建后面两页 再创建...
						for(var i=0;i<2;i++){
							this.pageLis.eq(index++).find("a span").html(pageNum+i+1);
					    }
						if(pageNum+3!==this.acount)
							this.pageLis.eq(index++).addClass('not').find("a span").html("...");
						//再根据页数创建最后两页或一页
						if(this.acount-pageNum>4){
							this.pageLis.eq(index++).find("a span").html(this.acount-1);
			                this.pageLis.eq(index++).find("a span").html(this.acount);
					}else{
							this.pageLis.eq(index++).find("a span").html(this.acount);
					}
					}
					for(var length =this.pageLis.length;index<length;index++){
			               this.pageLis.eq(index).addClass('hide');
					}
					this.isLast();
				},
				//响应按钮点击事件
				control:function(){
					var that = this;
					$("body").on('click', '#inner .item a', function(event) {
						event.preventDefault();
						that.Current = 1;
						that.lastPage = Number($('#inner .items .current').find('a span').html());
						if($(this).parent().hasClass('next')){
							that.Current = that.lastPage+1;
					    }
						else if($(this).parent().hasClass('pre')){
							that.Current = that.lastPage-1;
					    }
						else
						that.Current = $(this).find('span').eq(0).html();
						that.fn(that.Current);
						that.change(that.Current);
					});
				}
			}
		   return function(amount,data,id,num,fn)
		    {
		       fenye.init(amount,data,id,num,fn);
		    }
		 })();
 return {
 	division:test
 }
});