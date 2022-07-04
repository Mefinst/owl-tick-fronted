<style>
.tick-app {
	display: flex;
	flex-flow: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
}
.surface {
	border: var(--surface-border) solid 2px;
	border-radius: 7px;
	padding: 1cm 1.5cm;
	background: var(--surface-background);
}
.surface h1 {
	margin-top: 0;
}
</style>

<template>
	<div class="tick-app">
		<div class="surface">
			<h1>Owl Tick Tracker</h1>
			<router-view></router-view>
		</div>
	</div>
</template>

<script setup></script>
