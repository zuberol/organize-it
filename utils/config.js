const CODE_DIRS = [
    "/Users/zuber/js/organize-it/receivedFiles/zeauberg/code-samples/java",
    "/Users/zuber/js/ref/react-scripts"
]
// const SCOPES = ['https://www.googleapis.com/auth/spreadsheets', 'https://www.googleapis.com/auth/calendar.readonly'];
const SCOPES = ['https://www.googleapis.com/auth/calendar.readonly'];
const TOKEN_PATH = 'token.json';

const FRONTEND_BASE_URL = "http://localhost:3000"
const BACKEND_BASE_URL = "http://localhost:8080"

module.exports = {
    CODE_DIRS: CODE_DIRS,
    SCOPES: SCOPES,
    TOKEN_PATH: TOKEN_PATH,
    BACKEND_BASE_URL: BACKEND_BASE_URL,
    FRONTEND_BASE_URL: FRONTEND_BASE_URL
}
