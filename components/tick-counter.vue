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
import { computed, ref, watchEffect } from 'vue'
import { fold, fromNullable, map } from 'fp-ts/Option'
import { pipe } from 'fp-ts/function'

const props = defineProps({ initialValue: { type: Number } })

const tickCount = ref(0)
const increment = () => (tickCount.value += 1)

const initialValue = ref(localStorage.getItem('ticks'))
const initialValueWrapped = computed(() => fromNullable(initialValue.value))
watchEffect(() => {
	tickCount.value = pipe(
		initialValueWrapped.value,
		map((it) => Number(it)),
		fold(
			() => 0,
			(value) => Number(value)
		)
	)
})

watchEffect(() => localStorage.setItem('ticks', String(tickCount.value)))
</script>
