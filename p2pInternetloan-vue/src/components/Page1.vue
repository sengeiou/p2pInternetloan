<template>
  <div class="hello">
    <h1>Page1,{{ ts }}</h1>
    <!--<h2>获取state中的属性（不推荐）:{{resturantName}}</h2>-->
    <h2>{{resturantName}}</h2>
  </div>
</template>

<script>
	 import { mapGetters } from 'vuex'
export default {
  name: 'Page1',
  data:function () {
    return {
      ts: new Date().getTime()
    }
  },
  computed:{
//	resturantName:function(){
//		//不推荐
//		return this.$store.state.resturantName;
//	}
		...mapGetters({
			name:'getResturantName',
			resturantName: 'getResturantName'
		})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.hello{
		margin: 10px;
	}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
