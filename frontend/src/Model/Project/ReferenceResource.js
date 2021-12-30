
import AceEditor from "react-ace";
import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/theme-github";
import { js_beautify } from "js-beautify";

export function renderResources(referenceResources) {
    if (!referenceResources) return null;
    return (
        <ul>
            {referenceResources.map(res => <li key={res.resId}> {getRefResourceComponent(res)} </li>)}
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
    const { caption, comment, referenceUrl } = props.resource;
    return (
        <div>
            {caption && <p>{caption}</p>}
            {comment && <p>{comment}</p>}
            {referenceUrl && <a href={referenceUrl}>{referenceUrl}</a>}
        </div>
    )
}

function CodeReference(props) {
    const { sourceCode } = props.resource;
    return (
        <div>
            <SimpleReference resource={props.resource} />
            <AceEditor 
                mode="java"
                theme="github"
                value={js_beautify(sourceCode, { indent_size : 2 })}
                readOnly={true}
            />
        </div>
    )
}
