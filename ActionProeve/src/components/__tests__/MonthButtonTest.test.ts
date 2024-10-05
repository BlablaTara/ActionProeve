import { describe, it, expect } from 'vitest';
import {mount} from "@vue/test-utils";
/*import MonthCalender from "@/components/MonthCalender.vue";

describe('MonthView', () => {
    it('changes the month when prevMonth button is clicked', async () => {
        const wrapper = mount(MonthCalender);

        //Check initial month and year values
        const initialMonth = wrapper.vm.currentMonth.value;
        const initialYear = wrapper.vm.currentYear.value;

        //Click the prevMonth button
        await wrapper.find('button[type="button"]:first-of-type').trigger('click'); // Assuming the first button is prevMonth

        //Check that the month has changed
        const newMonth = wrapper.vm.currentMonth.value;
        let newYear = wrapper.vm.currentYear.value;

        if (initialMonth === 0) {
            //If it was January (0), it should wrap to December (11) and the year should decrease
            expect(newMonth).toBe(11);
            expect(newYear).toBe(initialYear - 1);
        } else {
            //Otherwise, it should just decrement the month
            expect(newMonth).toBe(initialMonth - 1);
            expect(newYear).toBe(initialYear);
        }
    });
});

describe('MonthView', () => {
    it('changes the month when nextMonth button is clicked', async () => {
        const wrapper = mount(MonthCalender);

        //Check initial month and year values
        const initialMonth = wrapper.vm.currentMonth.value;
        const initialYear = wrapper.vm.currentYear.value;

        //Click the nextMonth button
        await wrapper.find('button[type="button"]:last-of-type').trigger('click'); // Assuming the last button is nextMonth

        //Check that the month has changed
        const newMonth = wrapper.vm.currentMonth.value;
        let newYear = wrapper.vm.currentYear.value;

        if (initialMonth === 11) {
            //If it was December (11), it should wrap to January (0) and the year should increase
            expect(newMonth).toBe(0);
            expect(newYear).toBe(initialYear + 1);
        } else {
            //Otherwise, it should just increment the month
            expect(newMonth).toBe(initialMonth + 1);
            expect(newYear).toBe(initialYear);
        }
    });
});*/