import { BACKEND_BASE_URL } from "./main"; 

// FLASHCARDS
export const DECK_URL = new URL("/api/deck", BACKEND_BASE_URL)
export const DECKS_URL = new URL("/api/decks", BACKEND_BASE_URL)
export const FLASHCARD_URL = new URL("/api/flashcard", BACKEND_BASE_URL)

// TASKS
export const PROJECTS_URL = new URL("/api/projects", BACKEND_BASE_URL)
export const TASK_URL = new URL("/api/task", BACKEND_BASE_URL)
export const TASK_NEW_URL = new URL("/api/task/new", BACKEND_BASE_URL)
export const TASK_INBOX_URL = new URL("/api/projects?projectName=inbox", BACKEND_BASE_URL)
export const TASK_SUBTASK_PUT_URL = new URL("/api/task/subtask/put", BACKEND_BASE_URL)

// MEAL
export const MEAL_URL = new URL('/api/meal', BACKEND_BASE_URL)

// DEV
export const FILECHECK_URL = new URL('/api/dev/filecheck', BACKEND_BASE_URL);

// SNIPPETS
export const SNIPPETS_URL = new URL('/api/snippets', BACKEND_BASE_URL);
