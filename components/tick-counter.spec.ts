import { beforeEach, describe, expect, test } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import TickCounter from './tick-counter.vue'
import { nextTick } from 'vue'
describe('Tick Counter', () => {
	let wrapper = null
	const getCounter = () => wrapper.find('.tick-counter__ticks')
	const getIncrement = () => wrapper.find('button.tick-counter__increment')
	beforeEach(() => {
		wrapper = shallowMount(TickCounter)
	})

	test('renders tick counter', () => {
		expect(getCounter().exists())
		expect(getCounter().text()).toBe('Ticks: 0')
	})

	test('renders increment button', () => {
		expect(getIncrement().exists())
		expect(getIncrement().text()).toBe('Increment')
	})

	test('increment button should increment counter', async () => {
		expect(getCounter().text()).toBe('Ticks: 0')

		await getIncrement().trigger('click')
		await nextTick()

		expect(getCounter().text()).toBe('Ticks: 1')

		await getIncrement().trigger('click')
		await nextTick()

		expect(getCounter().text()).toBe('Ticks: 2')
	})
})
