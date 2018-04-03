<template>
  <div class="paging">
    <div class="number">
      <a :class="{noClick:pagingData.pageNum==1}" @click="jumps(pagingData.prePage,0)" href="javascript:"
         class="up noClick"><s></s></a>
      <a v-for="item in navigatepageNums" @click="jumps(item)" :class="{cur:pagingData.pageNum==item}"
         href="javascript:">{{item}}</a>
      <a :class="{noClick:pagingData.pageNum==pagingData.pages}" @click="jumps(pagingData.nextPage,1)"
         href="javascript:" class="down"><s></s></a>
    </div>
    <div class="pageJump">
      <span>共{{pagingData.pages}}页，跳转到</span>
      <input v-model="pageLabel" type="text"/>
      <span>页</span>
      <button @click="designation" class="" href="javascript:">确定</button>
    </div>
  </div>

</template>
<script type="text/ecmascript-6">
  export default {
    name: 'paging',
    data() {
      return {pageLabel:''}
    },
    computed: {
      navigatepageNums() {
        return this.pagingData && this.pagingData.navigatepageNums;
      }
    },
    methods: {
      designation:function () {
        let _this= this;
        if(/^\+?[1-9][0-9]*$/.test(_this.pageLabel)){
          if(_this.pageLabel > _this.pagingData.pages){
            return false;
          }
          this.jumps(_this.pageLabel);
        }
      },
      jumps: function (pageNum, direction) {
        if (direction === 0 && pageNum === 0) {/* 判断是否为上一页 */
          return false;
        }
        if (direction === 1 && pageNum === this.pagingData.pages) {/* 判断是否为下一页 */
          return false;
        }
        if (pageNum == this.pagingData.pageNum) {
          return false;
        }
        this.$emit('jumps', pageNum)
      }
    },
    props: ['pagingData']
  }

</script>
<style>
  /*  */
  .paging {
    padding: 60px 0;
    text-align: center;
  }



  /* 页数显示部分 */
  .paging  .number{
    overflow: hidden;
    display: inline-block;
  }
  .paging .number a {
    float: left;
    width: 40px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    background: #f7f7f7;
    border-radius: 4px;
    margin: 0 5px;
    color: #a1a1a1;
  }

  .paging .number .cur {
    background: #f26552;
    color: #fff;
  }

  .paging .number a s {
    width: 13px;
    height: 13px;
    background: url(./../../../static/img/iconC.png) -123px -44px;
    margin: 8px 0 0 0;
    float: none;
  }

  .paging .number .up,
  .paging .number .down {
    width: 50px;
    height: 28px;
  }

  .paging .number .noClick:hover {
    color: #e5e5e5;
    cursor: not-allowed;
  }

  .paging .number .down s {
    background: url(./../../../static/img/iconC.png) -128px -28px;
  }

  /* 跳转部分 */
  .paging .pageJump {
    display: inline-block;    overflow: hidden;    height: 28px;line-height: 28px;
  }
  .paging .pageJump>*{
    float: left;
  }
  .paging .pageJump input {
width: 50px;margin: 5px 7px 0;
  }
  .paging .pageJump button{
    padding: 4px 5px;    background: #f7f7f7;
    margin: 2px 5px 0;border-radius: 4px;
  }
  .paging .pageJump button:hover{
    background: #f26552;color:#fff;
  }


</style>

