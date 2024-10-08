import { mount } from '@vue/test-utils'
import CreateActivityForm from '../../components/CreateActivityForm.vue';
import { describe, expect, it } from 'vitest'

describe('AddActivityForm.vue', () => {
  it('renders the first step of the form', () => {
    const wrapper = mount(CreateActivityForm)

    // Initially, the form should show step 1
    expect(wrapper.find('input').attributes('placeholder')).toBe('fx:Badminton')
  })

  it('proceeds to the second step when "Next" is clicked', async () => {
    const wrapper = mount(CreateActivityForm)

    // Simulate filling in the first step and clicking next
    await wrapper.find('input').setValue('My New Activity')
    await wrapper.find('button').trigger('click')

    // Now the second input should appear
    expect(wrapper.find('input').attributes('placeholder')).toBe('fx:Our badminton court can fit up to 4 people...')
  })

})
