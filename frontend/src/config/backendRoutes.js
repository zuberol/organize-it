import { BACKEND_BASE_URL } from "./main"; 

// FLASHCARDS
export const DECK = new URL("/api/deck", BACKEND_BASE_URL)
export const DECKS = new URL("/api/decks", BACKEND_BASE_URL)
export const FLASHCARD = new URL("/api/flashcard", BACKEND_BASE_URL)

// TASKS
export const PROJECT = new URL("/api/project", BACKEND_BASE_URL)
export const PROJECTS = new URL("/api/projects", BACKEND_BASE_URL)
export const TASK = new URL("/api/task", BACKEND_BASE_URL)
export const TASK_SUBTASK_PUT = new URL("/api/task/subtask/put", BACKEND_BASE_URL)

// MEAL
export const MEAL = new URL('/api/meal', BACKEND_BASE_URL)

// DEV
export const FILECHECK = new URL('/api/dev/filecheck', BACKEND_BASE_URL);