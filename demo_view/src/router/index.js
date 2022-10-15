import VueRouter from 'vue-router'
import Vue from 'vue'
import PunchRegister from "../views/PunchRegister"
import PunchRecord from "../views/PunchRecord"

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path:"/",
            redirect:"register"
        },
        {
            name:"PunchRegister",
            path:"/register",
            component:PunchRegister
        },
        {
            name:"PunchRecord",
            path:"/record",
            component:PunchRecord
        }
    ]
})