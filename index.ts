import { createApp } from 'vue'
import TickCounter from './components/tick-counter.vue'

const tickCounter = createApp(TickCounter)

tickCounter.mount('[data-component="tick-counter"]')
