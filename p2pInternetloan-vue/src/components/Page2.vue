<template>
  <div class="hello">
    <h1>Page2,{{ ts }}</h1>
    <h2>从getters中获取属性:{{resturantName}}</h2>
    <h2>Jwt:{{jwtName}}</h2>
    <div>
    	<button @click="doMutation">Mutation之同步方式</button>
    </div>
    <div>
    	<button @click="doActionStatic">Action之异步方式操作静态数据</button>
    </div>
     <div>
    	<button @click="doAction">Action之异步方式操作动态数据</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Page2',
  data:function () {
    return {
      ts: new Date().getTime()
    } 
  },
  methods:{
  	doMutation:function(){
//		this.$store.getters.getResturantName;
			this.$store.commit('setResturantName',{
				resturantName:'肯德基'
			});
  	},
  	doActionStatic:function(){
  		this.$store.dispatch('setResturantNameAsync',{
  			resturantName:'金拱门'
  		});
  	},
  	doAction:function(){
  		this.$store.dispatch('setResturantNameAsyncAjax',{
  			resturantName:'必胜客',
  			_this:this
  		});
  	}
  },
  computed:{
  	resturantName:function(){
  		//不推荐
  		//return this.$store.state.resturantName;
  		return this.$store.getters.getResturantName;
  	},
  	jwtName:function(){
  		return this.$store.getters.getJwt;
  	}
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
