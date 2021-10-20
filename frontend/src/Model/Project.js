import Task from './Task';

export class Project {
    constructor({project_id, title = "", description = "", root_task = Task.empty, tags = []}) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.root_task = new Task(root_task);
        this.tags = tags;
        this['@class'] = "com.zuber.organizeit.Model.Project";
    }

    static ['@class'] = "com.zuber.organizeit.Model.Project"; // todo remove

    static empty() {
        return new Project({});
    }

    static recordToProject(projectRecord) {
        let project = Project.empty();
        if(projectRecord["@class"] === Project["@class"]) project = new Project({...projectRecord});
        return project;
    }

}