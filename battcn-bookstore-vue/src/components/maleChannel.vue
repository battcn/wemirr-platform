<template><!-- 男频 -->
  <div class="male">
    <div class="f1">
      <div class="f1_L">
        <div class="pl_pic">
          <ul>
            <li><a href="javascript:"><img src="./../../static/img/male.jpg" alt=""></a></li>
            <li><a href="javascript:"><img src="./../../static/img/male.jpg" alt=""></a></li>
          </ul>
        </div>
        <ol class="pl_dot">
          <li class="on"></li>
          <li></li>
        </ol>
      </div>
      <div class="f1_R">
        <recomm_side></recomm_side>
      </div>
    </div>
    <div class="f2">
      <leader_infeed></leader_infeed>
    </div>
    <strip></strip>
    <div class="f3">
      <div class="f3_L">
        <h2 class="tit"><s></s>神之领域</h2>
        <div class="f3_L_L">
          <div class="pl_pic">
            <ul>
              <li><a href=""><img src="./../../static/img/male.jpg" alt=""></a></li>
            </ul>
          </div>
          <ol class="dot"><li class="on"></li></ol>
          <ul class="sts">
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
            <li><span><a href="">[ 都市异能 ]</a></span><a href="">神豪朋友圈 </a></li>
          </ul>
        </div>
        <ul class="f3_f5_C">
          <li>
            <a href=""><img src="./../../static/img/male.jpg" alt=""></a>
            <div class="book_inf">
              <h3><a href="">绝世龙帝</a></h3>
              <p class="aut">作者：一修</p>
              <p class="int">龙辰以大帝之体成就十方天域最年轻的主宰强者，一生为逆转轮回复活至亲而努力！
                然而却被最信任的师尊背叛，在无数强者围攻之下陨落。待他再次睁眼却携世间第一秘典逆转轮回，重回三百年前，这时家族未灭，他大帝之体尚未觉醒……
                这一世，他要一生无敌！
                这一世，他要再无悔恨！
                这一世，他要以手中之剑，斩尽天下敌，从此登临前所未有的大帝之位。</p>
            </div>
          </li>
          <li>
            <a href=""><img src="./../../static/img/male.jpg" alt=""></a>
            <div class="book_inf">
              <h3><a href="">绝世龙帝</a></h3>
              <p class="aut">作者：一修</p>
              <p class="int">龙辰以大帝之体成就十方天域最年轻的主宰强者，一生为逆转轮回复活至亲而努力！
                然而却被最信任的师尊背叛，在无数强者围攻之下陨落。待他再次睁眼却携世间第一秘典逆转轮回，重回三百年前，这时家族未灭，他大帝之体尚未觉醒……
                这一世，他要一生无敌！
                这一世，他要再无悔恨！
                这一世，他要以手中之剑，斩尽天下敌，从此登临前所未有的大帝之位。</p>
            </div>
          </li>
          <li>
            <a href=""><img src="./../../static/img/male.jpg" alt=""></a>
            <div class="book_inf">
              <h3><a href="">绝世龙帝</a></h3>
              <p class="aut">作者：一修</p>
              <p class="int">龙辰以大帝之体成就十方天域最年轻的主宰强者，一生为逆转轮回复活至亲而努力！
                然而却被最信任的师尊背叛，在无数强者围攻之下陨落。待他再次睁眼却携世间第一秘典逆转轮回，重回三百年前，这时家族未灭，他大帝之体尚未觉醒……
                这一世，他要一生无敌！
                这一世，他要再无悔恨！
                这一世，他要以手中之剑，斩尽天下敌，从此登临前所未有的大帝之位。</p>
            </div>
          </li>
          <li>
            <a href=""><img src="./../../static/img/male.jpg" alt=""></a>
            <div class="book_inf">
              <h3><a href="">绝世龙帝</a></h3>
              <p class="aut">作者：一修</p>
              <p class="int">龙辰以大帝之体成就十方天域最年轻的主宰强者，一生为逆转轮回复活至亲而努力！
                然而却被最信任的师尊背叛，在无数强者围攻之下陨落。待他再次睁眼却携世间第一秘典逆转轮回，重回三百年前，这时家族未灭，他大帝之体尚未觉醒……
                这一世，他要一生无敌！
                这一世，他要再无悔恨！
                这一世，他要以手中之剑，斩尽天下敌，从此登临前所未有的大帝之位。</p>
            </div>
          </li>
        </ul>
      </div>
      <div class="f3_R">
        <leader></leader>
      </div>
    </div>
    <strip></strip>
  </div>
</template>
<script type="text/ecmascript-6">
  import leader_infeed from './children/leader_infeed.vue'
  import recomm_side from './children/recomm_side.vue'
  import strip from './children/strip.vue'
  import leader from './children/leaderboard.vue'
  export  default {
    name:'maleChannel',
    data(){return {} },
    components:{leader_infeed,strip,leader,recomm_side}
  }
</script>
<style>
  .male .f1,.male .f2,.male .f3{
    background: #fff;
    margin: 0 0 20px 0;overflow: hidden;
  }
  /* 第一区域 左边部分 */
  .male .f1 .f1_L{
    float: left;
    width: 630px;
    padding: 20px 0 0 0;
  }
  .male .f1 .f1_L .pl_pic{
    width: 576px;
    height: 320px;
    border: 1px solid #f3f3f3;
    overflow: hidden;
    position: relative;
    margin: 0 auto;
  }
  .male .f1 .f1_L .pl_pic > ul{position: relative;
    left: 0;}
  .male .f1 .f1_L .pl_pic > ul li{
    float: left;
  }
  .male .f1 .f1_L .pl_pic > ul li a{
    width: 576px;
    height: 320px;
    border: none;
  }
  .male .f1 .f1_L .pl_pic > ul li a img{
    width: 100%;
    height: 100%;
    transition: all 0.5s;
  }
  /* 轮播下方指示点 */
  .male .f1 .f1_L .pl_dot{
    overflow: hidden;margin: 20px auto 0;
    width: 124px;
  }
  .male .f1 .f1_L .pl_dot li{
    width: 14px;
    height: 14px;
    border-radius: 50%;
    background: #3498db;
    margin: 8px 17px 0;
    cursor: pointer;
    float: left;
  }
  .male .f1 .f1_L .pl_dot li.on{
    width: 42px;
    border-radius: 40px;
  }
  /* 第一区域 右边部分 */
  .male .f1 .f1_R{
    float: left;
    border-left: 1px solid #f0f0f0;
  }

  /* 第3部分 */
  .male .f3 .f3_L{    width: 828px;
    height: 594px;
    border-right: 1px solid #f0f0f0;
    float: left;}
  .male .f3 .f3_L .tit{    height: 39px;
    border-bottom: 1px solid #3498db;
    font-size: 20px;
    padding: 20px 0 0 48px;
    position: relative;}
  .male .f3 .f3_L .tit s{width: 55px;
    height: 58px;
    position: absolute;
    left: 0;background: url("./../../static/img/pdIcon.png") no-repeat;
    background-position: -78px -30px;
    top: 0;}
  .male .f3 .f3_L .f3_L_L{width: 490px;
    padding: 20px 38px 0;
    float: left;}
  .male .f3 .f3_L .f3_L_L .pl_pic{    width: 486px;
    height: 270px;border: 1px solid #f3f3f3;
    overflow: hidden;
    margin: 0 auto;}
  .male .f3 .f3_L .f3_L_L .pl_pic ul{    position: relative;
    width: 486px;
    left: 0;        overflow: hidden;
  }
  .male .f3 .f3_L .f3_L_L .pl_pic ul li{float: left;}
  .male .f3 .f3_L .f3_L_L .pl_pic ul li a{    width: 486px;
    height: 270px;}
  .male .f3 .f3_L .f3_L_L .pl_pic ul li a img{}
  .male .f3 .f3_L .f3_L_L .dot{    margin: 0 auto;
    padding: 20px 0;    overflow: hidden;
    width: 76px;}
  .male .f3 .f3_L .f3_L_L .dot li{width: 14px;
    height: 14px;
    border-radius: 50%;
    background: #3498db;
    margin: 8px 17px 0;
    cursor: pointer;
    float: left;}
  .male .f3 .f3_L .f3_L_L .dot li.on{width: 42px;
    border-radius: 40px;}
  .male .f3 .f3_L .f3_L_L .sts{overflow: hidden;
    border-top: 1px solid #c6c9cc;
    padding: 16px 0 0 0;}
  .male .f3 .f3_L .f3_L_L .sts li{    float: left;
    width: 230px;
    line-height: 32px;}
  .male .f3 .f3_L .f3_L_L .sts li:nth-child(2n){
    float: right;
  }
  .male .f3 .f3_L .f3_L_L .sts li span{    margin-right: 2px;}
  .male .f3 .f3_L .f3_L_L .sts li span a{color: #666;}
  .male .f3 .f3_L .f3_L_L .sts li > a{
    max-width: 170px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;}
  .male .f3 .f3_L .f3_f5_C{    padding: 20px 0 0 0;
    float: left;}
  .male .f3 .f3_L .f3_f5_C li{overflow: hidden;
    padding: 0 0 24px 0;}
  .male .f3 .f3_L .f3_f5_C li >a{width: 80px;
    float: left;height: 100px;
    margin: 0 18px 0 0;}
  .male .f3 .f3_L .f3_f5_C li a img{}
  .male .f3 .f3_L .f3_f5_C li .book_inf{width: 130px;
    float: left;}
  .male .f3 .f3_L .f3_f5_C li .book_inf h3{    font-size: 14px;}
  .male .f3 .f3_L .f3_f5_C li .book_inf h3 a{    max-width: 130px;}
  .male .f3 .f3_L .f3_f5_C li .book_inf p.aut{margin: 4px 0 25px 0;}
  .male .f3 .f3_L .f3_f5_C li .book_inf p.int{    height: 34px;
    overflow: hidden;font-size: 12px;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-wrap: break-word;}
  .male .f3  .f3_R{        float: left;
    padding: 34px 0 0 0;}
</style>

