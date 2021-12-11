
import AceEditor from "react-ace";
import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/theme-github";
import { js_beautify } from "js-beautify";

export function renderResources(reference_resources) {
    if (!reference_resources) return null;
    return (
        <ul>
            {reference_resources.map(res => <li key={res.res_id}> {getRefResourceComponent(res)} </li>)}
        </ul>
    )
}

export function getRefResourceComponent(resource) {
    if (!resource) return null;
    let resourceComponent;
    const { ['@class']: resType } = resource;
    switch (true) {
        case /code/i.test(resType):
            resourceComponent = <CodeReference resource={resource} />;
            break;
        default:
            resourceComponent = <SimpleReference resource={resource} />;
            break;
    }
    return resourceComponent;
}

function SimpleReference(props) {
    const { caption, comment, reference_url } = props.resource;
    return (
        <div>
            {caption && <p>{caption}</p>}
            {comment && <p>{comment}</p>}
            {reference_url && <a href={reference_url}>{reference_url}</a>}
        </div>
    )
}

function CodeReference(props) {
    const { source_code } = props.resource;
    return (
        <div>
            <SimpleReference resource={props.resource} />
            <AceEditor 
                mode="java"
                theme="github"
                value={js_beautify(source_code, { indent_size : 2 })}
                readOnly={true}
            />
        </div>
    )
}
