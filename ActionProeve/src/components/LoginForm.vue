<script setup lang="ts">
import { ref } from 'vue'
import axiosInstance from '@/utils/axiosInstance.ts'
import BaseInput from './BaseInput.vue'
import BaseButton from './BaseButton.vue'
import { useRouter } from 'vue-router'
import { isAxiosError } from 'axios' // Import this if needed

const router = useRouter()

const isFormVisible = ref<boolean>(true)
const password = ref<string>('')
const username = ref<string>('')
const errorMessage = ref<string>('')
const isLoading = ref<boolean>(false)

interface LoginResponse {
  token: string
}

async function handleSubmit() {
  isLoading.value = true
  errorMessage.value = ''

  try {
    const response = await axiosInstance.post<LoginResponse>('/login', {
      username: username.value,
      password: password.value
    })

    console.log(response)

    if (response.status === 200) {
      const token = response.data.token
      localStorage.setItem('authToken', token)

      router.push('/dashboard')
      isFormVisible.value = false
    }
  } catch (error: unknown) {
    console.error(error)
    if (isAxiosError(error) && error.response?.status === 401) {
      errorMessage.value = 'Invalid username or password'
    } else {
      errorMessage.value = 'Login failed. Please try again.'
    }
  } finally {
    isLoading.value = false
  }
}
</script>

<template>
  <div>
    <div v-if="isFormVisible" class="overlay">
      <div class="modal">
        <form @submit.prevent="handleSubmit">
          <BaseInput
            id="username"
            name="username"
            labelFor="username"
            labelText="Username"
            placeholder="JohnDoe"
            v-model="username"
            required
            aria-required="true"
          />
          <BaseInput
            id="password"
            name="password"
            type="password"
            labelFor="password"
            labelText="Password"
            placeholder="password"
            v-model="password"
            required
            aria-required="true"
          />
          <BaseButton
            type="submit"
            :text="isLoading ? 'Loading...' : 'Submit'"
            :disabled="isLoading"
          />
          <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  backdrop-filter: blur(10px);
  z-index: 1000;
}

.modal {
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  z-index: 1100;
  max-width: 400px;
  width: 100%;
}

form {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}

button {
  padding: 0.75rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #0056b3;
}

button:hover:not(:disabled) {
  background-color: #0056b3;
}

.error-message {
  color: red;
  margin-top: 10px;
  font-size: 0.9rem;
}
</style>
