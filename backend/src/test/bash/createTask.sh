curl http://localhost:8080/api/task -X POST -H "Content-Type: application/json" -H "Accept: application/json"  -d '{"taskId": 100, "parentTaskId": "1L", "note": "test case", "subTasks": null}'
