import { createApp } from 'vue'
import TickApp from './tick-app.vue'
import { router } from './routes/router'

const tickCounter = createApp(TickApp)
tickCounter.use(router)
tickCounter.mount('#app')
