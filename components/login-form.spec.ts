import { beforeEach, describe, expect, test } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import LoginForm from './login-form.vue'
describe('Login form', () => {
	let wrapper = null
	const getUsernameInput = () => wrapper.find('input[name="username"]')
	const getPasswordInput = () =>
		wrapper.find('input[name="password"][type="password"]')
	beforeEach(() => {
		wrapper = shallowMount(LoginForm)
	})

	test('should render username input', () => {
		expect(getUsernameInput().exists())
	})
	test('should render username input', () => {
		expect(getPasswordInput().exists())
	})
})
