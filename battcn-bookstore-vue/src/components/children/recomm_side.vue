<template>
  <div class="recomm_side">
    <ul class="sts">
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
    </ul>
    <ul class="sts">
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
    </ul>
    <ul class="sts">
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
      <li><a href="javascript:">绝世武魂</a></li>
    </ul>
  </div>
</template>
<script>
export default {
  name:'recomm_side',
  data(){
    return {}
  }
}
</script>
<style scoped>
  .recomm_side{

  }
  .recomm_side .sts{
    padding: 18px 12px 12px 12px;
    overflow: hidden;
    border-bottom: 1px solid #f0f0f0;
  }
  .recomm_side .sts:last-child{
    border-bottom: 0;
  }
  .recomm_side .sts li{
    width: 50%;
    float: left;
    margin: 0 0 1px 0;text-align: center;overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .recomm_side .sts li:first-child{
    width: 100%;
    text-align: center;
    margin: 0 0 8px 0;
  }
  .recomm_side .sts li a{
    color: #666;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .recomm_side .sts li a:hover{color:#f26552;}
  .recomm_side .sts li:first-child a{
    color: #3498db;
    font-size: 16px;
  }

</style>

