import { useState } from 'react';
import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';
import React from 'react';
import './../../common/Form/form.css';
import './../../common/Form/treeStructure.scss';

//todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
export default function AddTaskModal() {
    return (
        <main
         style={{
            //   display: "flex", 
            //   "justify-content": "center", 
              backgroundColor: '#F1E0C5', 
              'minHeight': '85vh' ,
                height: '200vh'
            }}
         >
            <div

             style={{ 
                 display: "flex", 
                 "justify-content": "center", 
                 backgroundColor: "#c9b79c",
                  width: "800px"
                }}
             
             >
                <div class="tree">
                {/* <ul>
                    <li><i class="fa fa-folder-open"></i> Project
                        <ul>
                        <li><i class="fa fa-folder-open"></i> Opened Folder <span>- 15kb</span>
                            <ul>
                            <li><i class="fa fa-folder-open"></i> css
                                <ul>
                                <li><i class="fa fa-code"></i> CSS Files <span>- 3kb</span>
                                </li>

                                </ul>
                            </li>
                            <li><i class="fa fa-folder"></i> Folder close <span>- 10kb</span>
                            </li>
                            <li><i class="fab fa-html5"></i> index.html</li>
                            <li><i class="fa fa-picture-o"></i> favicon.ico</li>
                            <li><i class="fa fa-picture-o"></i> favicon.ico</li>
                            <li><i class="fa fa-picture-o"></i> favicon.ico</li>
                            
                            </ul>
                        </li>
                        <li><i class="fa fa-folder"></i> Folder close <span>- 420kb</span>

                        </li>
                        </ul>
                    </li>

                    <li>
                        <ul>
                            <li>
                                dsa
                            </li>
                            <li>
                                dsa
                            </li>
                        </ul>
                    </li>

                </ul> */}
                {/* <ul>
                    <li><i ></i> Project yy
                        <ul>
                            <li><i ></i> 222222222222 <span>- 3kb</span></li>
                            <li className="working"><i ></i> 2222222<span>- 20kb</span></li>
                        </ul>
                    </li>
                    <li><i class=""></i> Project 2
                        <ul>
                            <li><i class=""></i> 33333 <span>- 3kb</span></li>
                            <li><i class=""></i> 2224444444444442222<span>- 20kb</span></li>
                        </ul>
                    </li>
                </ul> */}

                <ul>
                    lelellelle
                    <li><i ></i> Project yy
                        <ul> okokoko
                            <li><i ></i> 222222222222 <span>- 3kb</span></li>
                            <li className="working"><i ></i> 2222222<span>- 20kb</span></li>
                        </ul>
                    </li>
                    <li><i class=""></i> Project 2
                        <ul>okok
                            <li><i class=""></i> 33333 <span>- 3kb</span></li>
                            <li>yyyy<span>20kb</span></li>
                            <li><i class="">icon</i> 2224444444444442222<span>- 20kb</span></li>
                            <li><i class=""></i> 2224444444444442222<span>- 20kb</span></li>
                            <li><i class=""></i> 2224444444444442222<span>- 20kb</span></li>
                        </ul>
                    </li>
                </ul>
                </div>
            </div>
            <div style={{backgroundColor: "navy", height: "100px", width: '100%'}}>

            </div>

        </main>
    );


}


