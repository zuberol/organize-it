export default class Task {
    constructor({task_id = null, note = "", parent_task_id = null, sub_tasks = []}) {
        this.task_id = task_id;
        this.note = note;
        this.parent_task_id = parent_task_id;
        this.sub_tasks = sub_tasks;
    }
}