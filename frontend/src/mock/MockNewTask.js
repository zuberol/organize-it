export const MockTask2 = {
  "taskId": 13,
  "note": "podstawy fizyki",
  "subTasks": [
    {
      "taskId": 40,
      "note": "40 ogarnac 50",
      "subTasks": [],
      "parentId": "13"
    },
    {
      "taskId": 50,
      "note": "50 code pipeline",
      "subTasks": [],
      "parentId": {
        "taskId": 31,
        "note": "paaaarent task",
        "subTasks": [],
        "parentId": 42
      }
    }
  ],
  "parentId": 3
};

export const MockTask1 = {
  "taskId": 13,
  "note": "podstawy fizyki",
  "subTasks": [
    {
      "taskId": 40,
      "note": "40 ogarnac 50",
      "subTasks": [],
      "parentTask": 13
    },
    {
      "taskId": 50,
      "note": "50 code pipeline",
      "subTasks": [],
      "parentTask": 31
    }
  ],
  "parentTask": 3
};
