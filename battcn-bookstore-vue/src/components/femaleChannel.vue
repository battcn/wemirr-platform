<template><!-- 女频 -->
  <div class="female">
    <div class="f1 area">
      <div class="f1_L">
        <div class="L_img">
          <img :src="imgList[showIndex-1]" />
        </div>
        <ol class="options">
          <li :class="{on:showIndex==1}" @mouseenter="showIndex=1">
            <s class="arr"></s>
            <s class="xh">1</s>
            <i>总裁爹地超给力</i>
          </li>
          <li :class="{on:showIndex==2}" @mouseenter="showIndex=2">
            <s class="arr"></s>
            <s class="xh">2</s>
            <i>总裁爹地超给力</i>
          </li>
          <li :class="{on:showIndex==3}" @mouseenter="showIndex=3">
            <s class="arr"></s>
            <s class="xh">3</s>
            <i>总裁爹地超给力</i>
          </li>
          <li :class="{on:showIndex==4}" @mouseenter="showIndex=4">
            <s class="arr"></s>
            <s class="xh">4</s>
            <i>总裁爹地超给力</i>
          </li>
          <li :class="{on:showIndex==5}" @mouseenter="showIndex=5">
            <s class="arr"></s>
            <s class="xh">5</s>
            <i>总裁爹地超给力</i>
          </li>
        </ol>
        <div class="book_inf">
          <h3><a href="javascript:">{{intList&&intList[showIndex-1]&&intList[showIndex-1]['title']}}</a></h3>
          <p>{{intList&&intList[showIndex-1]&&intList[showIndex-1]['con']}}
          </p>
        </div>
      </div>
      <div class="f1_C">
        <recomm_side></recomm_side>
      </div>
      <div class="f1_R">
        <leader></leader>
      </div>
    </div>
    <div class="f2 area">
      <leader_infeed></leader_infeed>
    </div>
    <strip></strip>
    <div class="f3 area">
      <div class="f3_L">
        <h2>
          <s></s>火热新书
        </h2>
        <div class="f3_L_con">
          <div class="sw">
            <div class="sw_t">
              <a class="book_cov" href="">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <h3>
                  <a href="">一宠成瘾：顾少温柔宠 </a>
                </h3>
                <p class="aut">作者:栀寒九暖</p>
                <p class="int">一名小三劝退师，初次执行任务的时候一不小心走错房间，此后便不可收拾。
                  一纸婚约，却赔了余生。
                  顾承曦，站在帝都顶峰，冷漠冰冷的男人，宠起女人却不像话。
                  “獒獒不会乱咬人的，不用戴上狗嘴罩。”
                  “它舔你。”
                  什么，连狗的醋都吃。
                  ••••••</p>
              </div>
            </div>
            <ul class="sts">
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
            </ul>
          </div>
          <div class="sw">
            <div class="sw_t">
              <a class="book_cov" href="">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <h3>
                  <a href="">一宠成瘾：顾少温柔宠 </a>
                </h3>
                <p class="aut">作者:栀寒九暖</p>
                <p class="int">一名小三劝退师，初次执行任务的时候一不小心走错房间，此后便不可收拾。
                  一纸婚约，却赔了余生。
                  顾承曦，站在帝都顶峰，冷漠冰冷的男人，宠起女人却不像话。
                  “獒獒不会乱咬人的，不用戴上狗嘴罩。”
                  “它舔你。”
                  什么，连狗的醋都吃。
                  ••••••</p>
              </div>
            </div>
            <ul class="sts">
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
            </ul>
          </div>
        </div>
        <h2>
          <s></s>火热新书
        </h2>
        <div class="f3_L_con">
          <div class="sw">
            <div class="sw_t">
              <a class="book_cov" href="">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <h3>
                  <a href="">一宠成瘾：顾少温柔宠 </a>
                </h3>
                <p class="aut">作者:栀寒九暖</p>
                <p class="int">一名小三劝退师，初次执行任务的时候一不小心走错房间，此后便不可收拾。
                  一纸婚约，却赔了余生。
                  顾承曦，站在帝都顶峰，冷漠冰冷的男人，宠起女人却不像话。
                  “獒獒不会乱咬人的，不用戴上狗嘴罩。”
                  “它舔你。”
                  什么，连狗的醋都吃。
                  ••••••</p>
              </div>
            </div>
            <ul class="sts">
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
            </ul>
          </div>
          <div class="sw">
            <div class="sw_t">
              <a class="book_cov" href="">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <h3>
                  <a href="">一宠成瘾：顾少温柔宠 </a>
                </h3>
                <p class="aut">作者:栀寒九暖</p>
                <p class="int">一名小三劝退师，初次执行任务的时候一不小心走错房间，此后便不可收拾。
                  一纸婚约，却赔了余生。
                  顾承曦，站在帝都顶峰，冷漠冰冷的男人，宠起女人却不像话。
                  “獒獒不会乱咬人的，不用戴上狗嘴罩。”
                  “它舔你。”
                  什么，连狗的醋都吃。
                  ••••••</p>
              </div>
            </div>
            <ul class="sts">
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
              <li><span><a href="">[ 穿越时空 ]</a></span><a href="">绝世嫡女，王爷宠无度 </a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="f3_R">
        <leader></leader>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import recomm_side from './children/recomm_side.vue'
  import leader from './children/leaderboard.vue'
  import leader_infeed from './children/leader_infeed.vue'
  import strip from './children/strip.vue'
  export default {
    name:'femaleChannel',
    data(){
      return{showIndex:1,imgList:['./../../static/img/large.jpg','./../../static/img/large2.jpg','./../../static/img/large.jpg','./../../static/img/large2.jpg','./../../static/img/large.jpg'],
        intList:[{title:'总裁爹地超给力',con:'为了夺走她父亲的股权，她被老公和闺蜜联手设局出轨。一纸离婚扔在脸上，她被迫净身出户。'},
          {title:'帝少的心尖娇妻',con:'雾气朦胧里，他西装革履，寸寸逼近：“要么死，要么爱我！”倔强开口：“我选死！”'},
          {title:'总裁爹地惹不起',con:'一场渡假，被当小姐，回国还撞见未婚夫出轨，她怒然消失离开。五年后，她带着天才萌宝回归。'},
          {title:'隐婚娇妻有点甜',con:'黎安本想安安静静的在医学之路上一去不复返，哪里知道半路杀出一个程咬金，签下合约还以为是形婚，哪成想夜夜笙歌，一点都没闲着。'},
          {title:'总裁大人超给力',con:'“嫁给我，我可以替你报仇。”陆白，亚洲第一跨国集团帝晟集团总裁，商业界最可怕的男人。'}
        ]}
    },
    components:{recomm_side,leader,leader_infeed,strip}
  }
</script>
<style scoped>
  s{
    background: url("./../../static/img/pdIcon.png") no-repeat;
  }
  .area{
    margin: 0 0 20px 0;background: #fff;
  }
.female .f1,.female .f2,.female .f3{
     overflow: hidden;
}
.female .f1 .f1_L{
  overflow: hidden;
  width: 377px;
  float: left;
  padding: 34px 25px 0;
}
.female .f1 .f1_L .L_img{
  float: left;
  margin-right: 32px;
}
.female .f1 .f1_L .L_img img{
width: 200px;height:250px;
}
.female .f1 .f1_L  .options{
float: left;
}
.female .f1 .f1_L  .options li {
  width: 88px;cursor: pointer;
  position: relative;
  background: #fafafa;
  border: 1px solid #e9e8e8;
  height: 35px;line-height: 37px;margin-bottom: 16px;padding: 0 28px 0 14px;    font-size: 12px;
}
  .female .f1 .f1_L  .options li.on{
    background: #ff2860;
    border: 1px solid #ff2860;
    color: #fff;
  }
  .female .f1 .f1_L  .options li.on i{
    color:#fff;
  }
.female .f1 .f1_L  .options li .arr{
  width: 10px;
  height: 17px;
  background-position: -1px -210px;
  position: absolute;
  left: -11px;
  top: 8px;display: none;
}
  .female .f1 .f1_L  .options li.on .arr{
    display: block;
  }
.female .f1 .f1_L  .options li .xh{
  width: 11px;
  height: 30px;
  line-height: 17px;
  padding: 0 0 0 13px;
  background-position: -27px -165px;
  position: absolute;
  top: -1px;
  right: -1px;
  color: #fff;

}
.female .f1 .f1_L  .options li i{
  max-width: 88px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
  .female .f1 .f1_L  .book_inf{

  }
  .female .f1 .f1_L  .book_inf h3{
text-align: center;padding: 18px 0 16px 0;
  }
  .female .f1 .f1_L  .book_inf h3 a{
    color: #ff2860;
    font-size: 16px;
  }
  .female .f1 .f1_L  .book_inf h3 a:hover{
    color: #f26552;
  }
  .female .f1 .f1_L  .book_inf p{
    height: 50px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    line-clamp: 3;
    -webkit-box-orient: vertical;
  }
  .female .f1 .f1_C{
    width: 422px;
    height: 445px;
    float: left;
    border-right: 1px solid #f0f0f0;
    border-left: 1px solid #f0f0f0;
  }
  .female .f1 .f1_R,.f3_R{
    overflow: hidden;    padding: 20px 0 0 20px;
  }
  /* 收入榜单组件 */
.female .f2{

}
.female  .f3{

}
  .female  .f3 .f3_L{
    width: 828px;
    height: 680px;
    border-right: 1px solid #f0f0f0;
    float: left;
  }
  .female  .f3 .f3_L h2{
    border-bottom: 1px solid #ff2860;    font-size: 20px;
    padding: 20px 0 0 48px;
    position: relative;    height: 39px;
  }
  .female  .f3 .f3_L h2 s{
    background-position: -62px -161px;    width: 55px;
    height: 58px;
    position: absolute;
    left: 0;
    top: 0;
  }
  .female  .f3 .f3_L  .f3_L_con{
    padding: 20px 33px 0;
    overflow: hidden;
  }
  .female  .f3 .f3_L  .f3_L_con .sw{
    width: 310px;
    height: 224px;
    background: #fafafa;
    float: left;
    padding: 16px 26px 0;
  }
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t{    overflow: hidden;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t> a{width: 72px;
    height: 90px;
    margin-right: 16px;
    float: left;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t a img{    display: inline;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t .book_inf{    width: 218px;
    float: left;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t .book_inf h3{}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t .book_inf h3 a{    max-width: 218px;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t .book_inf .aut{margin: 4px 0 16px 0;
    max-width: 220px;}
  .female  .f3 .f3_L  .f3_L_con .sw .sw_t .book_inf .int{}
  .female .f3 .f3_L ul.sts{
    padding: 18px 0 0 0;
  }
  .female .f3_L .sts li span{    margin: 0 14px 0 0;
    float: left;}
  .female .f3_L .sts li span a{
    max-width: 220px;color: #9e9e9e;
  }
  .female .f3_L .sts li a{max-width: 220px;color: #666;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;}
</style>
