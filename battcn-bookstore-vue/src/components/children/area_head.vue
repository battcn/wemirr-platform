<template>
  <div class="area_head">
    <span class="log_line"></span>
    <h4>
      <s class="hot"></s>
      {{title_name}}
    </h4>
    <span class="log_line"></span>
  </div>
</template>
<script>
  export default {
    name:'area_head',
    props:['title_name']
  }
</script>
<style >

  .area_head {
    overflow: hidden;
    margin-bottom: 34px;
  }
  .area_head .log_line {
    width: 402px;
    height: 2px;
    background: #f0f0f0;
    float: left;
    margin-top: 20px;
  }
  .area_head h4 {
    width: 160px;
    height: 38px;
    font-size: 28px;
    margin: 0 50px;
    float: left;
    color: #333;
  }
  .area_head h4 s {
    width: 26px;
    height: 30px;
    background-position: -1px -26px;
    margin: 3px 18px 0 0;
    float: left;
  }
  @media screen and (max-width:1100px){
    .area_head{margin: auto;}
    .area_head .log_line{display: none;}
    .area_head h4{width:100%;float: none;margin: auto;}
    .area_head h4 s{
    margin: 3px 4px 0 29px;
  }
  }

</style>
