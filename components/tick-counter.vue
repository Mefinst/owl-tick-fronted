<style></style>

<template>
	<div class="tick-counter">
		<div class="tick-counter__ticks">Ticks: {{ tickCount }}</div>
		<button class="tick-counter__increment" @click.prevent="increment">
			Increment
		</button>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const tickCount = ref(0)
const increment = () => (tickCount.value += 1)
</script>
