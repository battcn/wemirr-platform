<template>
  <div class="home">
    <div class="h_f1">
      <div class="h_f1_L">
        <!-- 轮播框 -->
        <div class="pl_pic">
          <ul :style="{left:pic_left+'px'}">
            <li><a href="javascript:" class=""><img src="./../../static/img/large.jpg" alt=""
                                                    class="lazyload_lb"></a>
              <div class="book_info font_12">
                <h3><a class="zdz" href="">最强升级</a></h3>
                <p class="int">
                  婚前记者采访某总裁：“慕先生，请问你到底喜欢你老婆什么地方？”慕先生一脸宠溺：“漂亮，温柔，而且十分乖巧。”
                </p>
              </div>
            </li>
            <li><a href="javascript:" class=""><img src="./../../static/img/large.jpg" alt=""
                                                    class="lazyload_lb"></a>
              <div class="book_info font_12">
                <h3><a class="zdz" href="">最强升级</a></h3>
                <p class="int">
                  婚前记者采访某总裁：“慕先生，请问你到底喜欢你老婆什么地方？”慕先生一脸宠溺：“漂亮，温柔，而且十分乖巧。”
                </p>
              </div>
            </li>
            <li><a href="javascript:" class=""><img src="./../../static/img/large2.jpg" alt=""
                                                    class="lazyload_lb"></a>
              <div class="book_info font_12">
                <h3><a class="zdz" href="">最强升级</a></h3>
                <p class="int">
                  婚前记者采访某总裁：“慕先生，请问你到底喜欢你老婆什么地方？”慕先生一脸宠溺：“漂亮，温柔，而且十分乖巧。”
                </p>
              </div>
            </li>
            <li><a href="javascript:" class=""><img src="./../../static/img/large.jpg" alt=""
                                                    class="lazyload_lb"></a>
              <div class="book_info font_12">
                <h3><a class="zdz" href="">最强升级</a></h3>
                <p class="int">
                  婚前记者采访某总裁：“慕先生，请问你到底喜欢你老婆什么地方？”慕先生一脸宠溺：“漂亮，温柔，而且十分乖巧。”
                </p>
              </div>
            </li>
          </ul>
        </div>
        <!--<div class="book_info font_12">
          <h3><a class="zdz" href="">最强升级</a></h3>
          <p class="int">
            婚前记者采访某总裁：“慕先生，请问你到底喜欢你老婆什么地方？”慕先生一脸宠溺：“漂亮，温柔，而且十分乖巧。”
          </p>
        </div>-->
        <div class="pl_dot">
          <ol>
            <li :class="{on:Carousel_pointer=='one'}"
                @mouseenter="Carousel_pointer='one',pic_left=0,timingCarousel(false)"
                @mouseleave="timingCarousel(true)"></li>
            <li :class="{on:Carousel_pointer=='two'}"
                @mouseenter="Carousel_pointer='two',pic_left=-500,timingCarousel(false)"
                @mouseleave="timingCarousel(true)"></li>
            <li :class="{on:Carousel_pointer=='three'}"
                @mouseenter="Carousel_pointer='three',pic_left=-1000,timingCarousel(false)"
                @mouseleave="timingCarousel(true)"></li>
            <li :class="{on:Carousel_pointer=='four'}"
                @mouseenter="Carousel_pointer='four',pic_left=-1500,timingCarousel(false)"
                @mouseleave="timingCarousel(true)"></li>
          </ol>
        </div>
      </div>
      <ul class="h_f1_C">
        <!-- 推荐 -->
        <li>
          <h3><a href=""> 天庭临时工</a></h3>
          <div>
            <a class="book_cover" href="">
              <img class="" src="../../static/img/small.jpg" alt="">
            </a>
            <div class="book_inf font_12">
              <span class="aut">我咬月亮</span>
              <span class="tag"><a href="">都市异能</a></span>
              <p class="int">仙人求我办事，美女逼我泡她，大佬给我倒茶，男神任我践踏。
                泡妞，我人见人爱；下毒，我百毒不侵；打架，我高手寂寞；炫富，我点石成金…我是天庭第一临时工，工作内容，惹桃花…</p>
            </div>
          </div>
        </li>
        <li>
          <h3><a href=""> 天庭临时工</a></h3>
          <div>
            <a class="book_cover" href="">
              <img class="" src="../../static/img/small.jpg" alt="">
            </a>
            <div class="book_inf font_12">
              <span class="aut">我咬月亮</span>
              <span class="tag"><a href="">都市异能</a></span>
              <p class="int">仙人求我办事，美女逼我泡她，大佬给我倒茶，男神任我践踏。
                泡妞，我人见人爱；下毒，我百毒不侵；打架，我高手寂寞；炫富，我点石成金…我是天庭第一临时工，工作内容，惹桃花…</p>
            </div>
          </div>
        </li>
        <li>
          <h3><a href=""> 天庭临时工</a></h3>
          <div>
            <a class="book_cover" href="">
              <img class="" src="../../static/img/small.jpg" alt="">
            </a>
            <div class="book_inf font_12">
              <span class="aut">我咬月亮</span>
              <span class="tag"><a href="">都市异能</a></span>
              <p class="int">仙人求我办事，美女逼我泡她，大佬给我倒茶，男神任我践踏。
                泡妞，我人见人爱；下毒，我百毒不侵；打架，我高手寂寞；炫富，我点石成金…我是天庭第一临时工，工作内容，惹桃花…</p>
            </div>
          </div>
        </li>
        <li>
          <h3><a href=""> 天庭临时工</a></h3>
          <div>
            <a class="book_cover" href="">
              <img class="" src="../../static/img/small.jpg" alt="">
            </a>
            <div class="book_inf font_12">
              <span class="aut">我咬月亮</span>
              <span class="tag"><a href="">都市异能</a></span>
              <p class="int">仙人求我办事，美女逼我泡她，大佬给我倒茶，男神任我践踏。
                泡妞，我人见人爱；下毒，我百毒不侵；打架，我高手寂寞；炫富，我点石成金…我是天庭第一临时工，工作内容，惹桃花…</p>
            </div>
          </div>
        </li>
      </ul>
      <leader :ListData="clickListData" style="padding: 0 20px 0;"></leader> <!-- 排行榜组件 -->
    </div>
    <strip></strip><!-- 图片长条 -->
    <!-- 热门推荐 开始 -->
    <div class="h_f2 ">
      <area_head :title_name="'热门推荐'"></area_head>
      <ul class="h_f2_list">
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover">
            <img src="./../../static/img/large.jpg" alt="" class="">
          </a>
          <div class="book_inf font_12">
            <h3><a href="">将军夫人别惹火</a></h3>
            <span class="aut">云霏霏</span>
          </div>
        </li>
      </ul>
    </div>
    <!-- 热门推荐 结束 -->
    <strip></strip><!-- 图片长条 -->
    <!-- 男生频道 开始 -->
    <div class="h_f3">
      <area_head :title_name="'男生频道'"></area_head>
      <div class="h_f3_L">
        <h3><a href=""> 战神升级系统</a></h3>
        <a href=""><img src="./../../static/img/large.jpg" alt="" class=""></a>
        <div class="book_inf font_12">
          <div class="book_inf01">
            <span class="aut">作者：七来</span>
            <span class="fr">分类：<a href="">东方玄幻</a></span>
            <span>字数：286万字+</span>
            <span class="fr">进度：连载中</span>
            <span>总点击：106508</span>
          </div>
          <p class="int">孙宁睡了一觉，醒来就成了大圣世界的一个太监。史上最杯具的穿越就此诞生！
            好在手机变成了战神系统！
            狂接任务，拼命升级，获取各种系统点数，兑换顶尖修炼资源！灵技战兵任性生产，宝丹真符批量制造。而孙宁最渴望的，还是赶紧攒够魅力值，得到还阳丹，成为正常男士……
            超级战神，横空出世，疯狂逆袭，从此开始。横扫各路天才，专治各种不服！一段从人间界碾压到诸天的激情岁月，开始了！</p>
        </div>
      </div>
      <div class="h_f3_C">
        <ul>
          <li>
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li>
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li class="no_bor">
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li class="no_bor">
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
        </ul>
      </div>
      <leader></leader> <!-- 排行榜组件 -->
    </div>
    <!-- 男生频道 结束 -->
    <strip></strip><!-- 图片长条 -->
    <!-- 女生频道 开始 -->
    <div class="h_f3">
      <area_head :title_name="'女生频道'"></area_head>
      <div class="h_f3_L">
        <h3><a href=""> 战神升级系统</a></h3>
        <a href=""><img src="./../../static/img/large.jpg" alt="" class=""></a>
        <div class="book_inf font_12">
          <div class="book_inf01">
            <span class="aut">作者：七来</span>
            <span class="fr">分类：<a href="">东方玄幻</a></span>
            <span>字数：286万字+</span>
            <span class="fr">进度：连载中</span>
            <span>总点击：106508</span>
          </div>
          <p class="int">孙宁睡了一觉，醒来就成了大圣世界的一个太监。史上最杯具的穿越就此诞生！
            好在手机变成了战神系统！
            狂接任务，拼命升级，获取各种系统点数，兑换顶尖修炼资源！灵技战兵任性生产，宝丹真符批量制造。而孙宁最渴望的，还是赶紧攒够魅力值，得到还阳丹，成为正常男士……
            超级战神，横空出世，疯狂逆袭，从此开始。横扫各路天才，专治各种不服！一段从人间界碾压到诸天的激情岁月，开始了！</p>
        </div>
      </div>
      <div class="h_f3_C">
        <ul>
          <li>
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li>
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li class="no_bor">
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
          <li class="no_bor">
            <h3><a href=""> 九转神龙诀</a></h3>
            <div class="book_inf font_12">
              <span class="aut">贪吃的地主</span>
              <span class="tag">
                <a href="">东方玄幻</a>
              </span>
              <a href="" class="book_cover">
                <img src="./../../static/img/large.jpg" alt="">
              </a>
              <p class="int">何谓强者？一念可碎星河！
                何谓弱者？一命如同蝼蚁！
                楚轩天纵奇才，为救父亲甘愿自废武魂，断绝前路！
                守孝三年，终得九转神龙诀，炼诸天星辰，踏万古青天，铸不朽神体！
                任你万般法门，我一剑皆可破之！
                剑气纵横十万里，一剑光寒三千界！
                楚轩：“我不问前尘，不求来生，只要这一世的轰轰烈烈！”</p>
            </div>
          </li>
        </ul>
      </div>
      <leader></leader> <!-- 排行榜组件 -->
    </div>
    <!-- 女生频道 结束 -->
    <!--精品推荐 开始-->
    <div class="h_f4">
      <div class="h_f4_L">
        <h2>精品推荐</h2>
      </div>
      <ul class="h_f4_R">
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
        <li>
          <a href="" class="book_cover"><img src="./../../static/img/large.jpg" alt=""></a>
          <div class="book_inf font_12">
            <h3><a href="">我的美女董事长</a></h3>
            <span class="aut">大侠一枝梅</span>
          </div>
        </li>
      </ul>
    </div>
    <!--精品推荐 结束-->
  </div>

</template>
<script type="text/ecmascript-6">
  import leader from './children/leaderboard.vue'
  /* 排行榜 */
  import strip from './children/strip.vue'
  /* 图片长条 */
  import area_head from './children/area_head.vue'
  /* 方块头条 */
  import {mapState,mapMutations} from 'vuex'
  export default {
    name: 'Home',
    data() {
      return {Carousel_pointer: 'one', pic_left: 0, Carousel_Time: "你好"}
    },
    computed:{
      clickListData(){/* 顶部排行榜数据 */
        return this.$store.state.initData.homeData&&this.$store.state.initData.homeData.homeClickList&&this.$store.state.initData.homeData.homeClickList.list;
      }
    },
    methods: {
      ...mapMutations([
        'iniHomeClickList'
      ]),
      iniData:function (token) {
        this.$store.commit('iniHomeClickList',token);
      },
      timingCarousel: function (isStart) {
        let _this = this;
        if (isStart) {
          this.Carousel_Time = setInterval(function () {
            switch (_this.pic_left) {
              case 0:
                _this.pic_left = _this.pic_left - 500;
                _this.Carousel_pointer = "two";
                break;
              case -500:
                _this.pic_left = _this.pic_left - 500;
                _this.Carousel_pointer = "three";
                break;
              case -1000:
                _this.pic_left = _this.pic_left - 500;
                _this.Carousel_pointer = "four";
                break;
              case -1500:
                _this.pic_left = 0;
                _this.Carousel_pointer = "one";
                break;
            }
          }, 3000);
        } else {
          clearInterval(this.Carousel_Time);
        }
      }
    },
    mounted() {
      this.timingCarousel(true);
      this.iniData();
    },
    components: {leader, strip, area_head}
  }

</script>
<style>
  /* 上部综合信息(轮播框、推荐点击榜) */
  .home .h_f1 {
    width: 1100px;
    background: #fff;
    overflow: hidden;
    padding: 20px 0 40px 0;
  }

  /* 轮播框 */
  .h_f1 .h_f1_L {
    width: 500px;
    float: left;
    overflow: hidden;
    padding: 0 18px 0;
  }

  /* 轮播框图片 */
  .h_f1 .h_f1_L .pl_pic {
    width: 500px;
    border-radius: 6px;
    border: 1px solid #f3f3f3;
    overflow: hidden;
  }

  .h_f1 .h_f1_L .pl_pic ul {
    position: relative;
    width: 2000px;
    overflow: hidden;
    transition: all .3s;

  }

  .h_f1 .h_f1_L .pl_pic ul li {
    float: left;
    width: 500px;
  }

  .h_f1 .h_f1_L .pl_pic ul li a {
    width: 500px;
    height: 250px;
    border: none;
  }

  /* 轮播框下方书籍简介 */
  .h_f1_L .book_info {
    width: 450px;
    margin: 0 auto;
  }

  .h_f1_L .pl_pic ul li .book_info h3 {
    height: 50px;
    text-align: center;
  }

  /* 轮播框书名 */
  .h_f1_L  .pl_pic ul li .book_info h3 a {
    max-width: 280px;
    margin-top: 26px;
  }

  .h_f1_L  .pl_pic ul li .book_info p {
    font-size: 12px;
    line-height: 18px;
    height: 34px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    word-wrap: break-word;
    text-align: left;
  }

  /* 轮播框指示小点 */
  .h_f1_L .pl_dot {
    height: 62px;
    border-bottom: 1px solid #c6c9cc;
  }

  .h_f1_L .pl_dot ol {
    width: 88px;
    overflow: hidden;
    padding-top: 28px;
    margin: 0 auto;
  }

  .h_f1_L .pl_dot ol li {
    cursor: pointer;
    width: 14px;
    height: 14px;
    background: #d2d2d2;
    border-radius: 50%;
    margin: 0 4px;
    float: left;
  }

  .h_f1_L .pl_dot ol .on {
    background: #f26552;
  }

  /* 第一区域中的中部推荐 */
  .h_f1_C {
    width: 262px;
    border-left: 1px solid #f0f0f0;
    border-right: 1px solid #f0f0f0;
    overflow: hidden;
    padding: 0 22px 0;
    float: left;
  }

  .h_f1_C li {
    overflow: hidden;
    margin-bottom: 23px;
  }

  .h_f1_C li > div {
    overflow: hidden;
  }

  .h_f1_C li h3 {
    font-size: 14px;
    width: 262px;
    border-bottom: 1px solid #f0f0f0;
    height: 24px;
    margin-bottom: 4px;
  }

  .h_f1_C li h3 a {
    max-width: 260px;
  }

  .h_f1_C li .book_cover {
    float: left;
    width: 60px;
    height: 75px;
    margin: 0 14px 0 4px;
  }

  .h_f1_C li .book_inf {
    float: right;
    width: 176px;
    margin-right: 4px;
  }

  .h_f1_C li .book_inf span {
    margin-top: 2px;
  }

  .h_f1_C li .book_inf .aut {
    max-width: 120px;
  }

  .h_f1_C li .book_inf .tag {
    float: right;
  }

  .h_f1_C li .book_inf .int {
    margin-top: 22px;
    height: 30px;
    overflow: hidden;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-wrap: break-word;
  }
  /* 首页热门推荐部分样式： */
  .h_f2, .h_f3 {
    width: 1064px;
    overflow: hidden;
    background: #fff;
    padding: 38px 18px 40px;
  }

  .h_f2 .h_f2_list {
    overflow: hidden;
    padding: 0 10px;
  }

  .h_f2 .h_f2_list li {
    overflow: hidden;
    width: 122px;
    margin: 6px 0;
    float: left;
    padding: 13px 26px;
  }

  .h_f2 .h_f2_list li:hover {
    box-shadow: 0 4px 7px rgba(0, 0, 0, .3);
    border-radius: 5px;
  }

  .h_f2 .h_f2_list li .book_cover {
    width: 120px;
    height: 150px;
  }

  .h_f2 .h_f2_list li .book_inf {
    text-align: center;
    margin-top: 8px;
  }

  .h_f2 .h_f2_list li h3 a {
    max-width: 120px;
  }

  .h_f2 .h_f2_list li .aut {
    max-width: 120px;
  }
/* 男女生频道样式 */
  .h_f3_L {
    float: left;
    width: 230px;
    padding: 5px 43px 0 25px;
  }

  .h_f3_L h3 {
    font-size: 24px;
    height: 80px;
  }

  .h_f3_L h3 a {
    max-width: 230px;
  }

  .h_f3_L .book_cover {
    width: 200px;
    height: 254px;
    margin: 10px auto 70px;
  }

  .h_f3_L .book_inf {
    overflow: hidden;
  }

  .h_f3_L .book_inf .book_inf01 {
    height: 66px;
    margin-bottom: 10px;
    overflow: hidden;
  }

  .h_f3_L .book_inf span {
    width: 115px;
    float: left;
    margin-bottom: 6px;
  }

  .h_f3_L .book_inf .fr {
    float: right;
    width: 84px;
  }
  .h_f3_C {
    width: 512px;
    overflow: hidden;
    border-left: 1px solid #f0f0f0;
    float: left;
  }

  .h_f3_C ul {
    overflow: hidden;
    height: 554px;
  }

  .h_f3_C ul li {
    width: 170px;
    height: 261px;
    padding: 16px 42px 0;
    border-right: 1px solid #f0f0f0;
    border-bottom: 1px solid #f0f0f0;
    float: left;
  }

  .h_f3_C ul li h3 {
    width: 170px;
    height: 22px;
    border-bottom: 1px solid #f0f0f0;
    margin-bottom: 8px;
  }

  .h_f3_C ul li h3 a {
    max-width: 160px;
  }

  .h_f3_C ul li .aut {
    max-width: 108px;
  }

  .h_f3_C ul li .book_cover {
    width: 100px;
    height: 125px;
    margin: 16px auto 10px;
  }

  .h_f3_C ul .no_bor {
    border-bottom: none;
  }
  /* 精品推荐样式 */
  .h_f4{
   margin-top: 20px;
    overflow: hidden;
    background: #fff;
  }
  /* 标题 */
  .h_f4_L{
    width: 74px;
    height: 200px;
    border-right: 1px solid #f0f0f0;
    float: left;
    margin: 15px 8px 0 0;
  }
  .h_f4_L h2{
    width: 22px;
    font-size: 22px;
    color: #333;
    word-wrap: break-word;
    letter-spacing: 20px;
    margin: 0 auto;
    padding-top: 40px;
  }
  /* 书籍排列 */
  .h_f4_R{
    float: left;
  }
  .h_f4_R li{
    padding: 23px 12px 22px;
    float: left;
    height: 185px;
  }
  .h_f4_R li .book_cover{
    width: 100px;
    height: 125px;
    margin-bottom: 16px;
  }
  .h_f4_R li .book_inf{
text-align: center;
  }

  /*响应式样式部分 */
  @media screen and (max-width:1100px) {
    .home .h_f1{width:100%;}
    /* 轮播框 */
    .h_f1 .h_f1_L{width: 80%;margin:0 auto;float: none;}
    .h_f1 .h_f1_L .pl_pic{width: 100%;}
    .h_f1 .h_f1_L .pl_pic ul{width: calc(100% * 4);}
    .h_f1 .h_f1_L .pl_pic ul li{width: calc(100% / 4);}
    .h_f1 .h_f1_L .pl_pic ul li a{width: 100%;}
  }

</style>


