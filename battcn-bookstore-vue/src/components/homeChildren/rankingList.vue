<template>
  <div class="com_R" :class="{com_bd:border==true}">
    <div class="list_tit">
      <h2><s></s>{{title}}</h2>
      <span :class="{show:cycle=='week'}" @mouseenter="cycle='week'">周</span>
      <span :class="{show:cycle=='month'}" @mouseenter="cycle='month'" >月</span>
    </div>
    <div class="list_wrap">
      <div class="swi">
        <ol v-show="cycle=='week'" class="list">
          <li @mouseenter="week_noun=1" :class="{lis_sh:week_noun==1}" class="">
            <em>1</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
          <li @mouseenter="week_noun=2" :class="{lis_sh:week_noun==2}" class="">
            <em>2</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
          <li @mouseenter="week_noun=3" :class="{lis_sh:week_noun==3}" class="">
            <em>3</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
        </ol>
        <ol v-show="cycle=='month'" class="list">
          <li @mouseenter="month_noun=1" :class="{lis_sh:month_noun==1}">
            <em>1</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large2.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
          <li @mouseenter="month_noun=2" :class="{lis_sh:month_noun==2}" class="">
            <em>2</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large2.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
          <li @mouseenter="month_noun=3" :class="{lis_sh:month_noun==3}" class="">
            <em>3</em>
            <h3 class="name"><a href="">绝世武魂</a></h3>
            <div class="open">
              <a class="book_cov" href="">
                <img class="lazyload_book_cover" src="./../../../static/img/large2.jpg" alt="">
              </a>
              <div class="book_inf">
                <span class="aut">作者：洛城东</span>
                <span>类别：<a href="">东方玄幻</a></span>
                <p class="int">龙脉大陆，万族林立，宗门无数，武者为尊。强者毁天灭地，弱者匍匐如蚁。
                  少年陈枫，丹田如铁，无法修炼，受尽冷眼。偶得至尊龙血，神秘古鼎，从此逆天崛起，横空出世！
                  娇俏妖狐，冷傲女皇，魔门妖女，神族公主，尽皆入我怀中。
                  修无上传承，凝最强武魂，坐拥众美，傲视九霄。</p>
              </div>
            </div>
          </li>
        </ol>
      </div>
      <span class="more">
            <a href="">
              <s></s>
              <i>查看更多</i>
              <s></s>
            </a>
          </span>
    </div>
  </div>
</template>
<script>
  export default {
    name:'rankingList',
    data(){
      return {week_noun:1,month_noun:1,cycle:'week',}
    },
    props:['title','border']
  }

</script>
<style scoped>
   .com_bd {
    border-left: 1px solid #f0f0f0;
    border-right: 1px solid #f0f0f0;
    width: 324px;
  }
   .com_R {
    width: 326px;
    overflow: hidden;
    padding: 20px 20px 0;
    font-size: 12px;
    border-bottom: 1px solid #f0f0f0;
  }
  .com_R .more {
    padding: 20px 0 30px 0;
  }
  .com_R .list_tit {
    overflow: hidden;
  }
  .com_R h2 {
    width: 220px;
    font-size: 18px;
    font-weight: normal;
    border: none;
    float: left;
  }
  .com_R h2 s {
    width: 9px;
    height: 17px;
    background-position: -141px -9px;
  }
  .com_R .list_wrap {
    width: 326px;
    overflow: hidden;
  }
  .com_R .list_wrap .swi {
    width: 1000px;
    overflow: hidden;
  }
  .com_R ol {
    width: 326px;
    overflow: hidden;
    border-bottom: 1px solid #f0f0f0;
    float: left;
  }
  .ki_li .com_R ol li em {
    background: none;
    color: #9e9e9e;
    margin-right: 10px;
  }
  .com_R ol li h3 {
    font-size: 12px;
    width: 220px;
  }
  .com_R ol li h3 a {
    max-width: 220px;
  }

   .ki_li .com_R ol li .open {
    width: 302px;
    height: 102px;
    padding: 10px 12px;
    margin-top: 8px;
    background: #fdfcfc;
    overflow: hidden;
  }
  .com_R ol li .open .book_cov {
    width: 80px;
    height: 100px;
  }
  .com_R ol li .book_inf {
    margin-left: 16px;
    text-align: left;
    width: 190px;
  }
  .com_R ol li .book_inf .aut {
     width: 192px;
   }
  .com_R ol li .book_inf .int {
    height: 48px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    margin-top: 16px;
  }
  .com_R .list_tit span {
    width: 42px;
    height: 24px;
    line-height: 24px;
    text-align: center;
    color: #333;
    border-radius: 4px;
    float: left;
    margin-left: 10px;
    cursor: pointer;
  }
  .com_R .list_tit .show {
    color: #fff;
    background: #c4c4c4;
  }

</style>

