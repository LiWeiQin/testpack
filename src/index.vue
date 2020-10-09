<template>
  <div class="wrapper">
    <wx-image-player style="width:1080;height:1080" imageUrls='["http://d.hiphotos.baidu.com/zhidao/pic/item/91ef76c6a7efce1b76a25faaaf51f3deb58f65c1.jpg","https://image.gameapps.hk/images/201505/01/5.jpg"]'></wx-image-player>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld'
export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data () {
    return {
      logo: 'https://gw.alicdn.com/tfs/TB1yopEdgoQMeJjy1XaXXcSsFXa-640-302.png'
    }
  }
}
</script>

<style scoped>
  .wrapper {
    justify-content: center;
    align-items: center;
  }
  .logo {
    width: 424px;
    height: 200px;
  }
  .greeting {
    text-align: center;
    margin-top: 70px;
    font-size: 50px;
    color: #41B883;
  }
  .message {
    margin: 30px;
    font-size: 32px;
    color: #727272;
  }
</style>
