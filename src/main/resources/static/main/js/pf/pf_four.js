import * as THREE from '/main/three.js-master/build/three.module.js';

import {OBJLoader} from '/main/three.js-master/examples/jsm/loaders/OBJLoader.js';
import {MTLLoader} from '/main/three.js-master/examples/jsm/loaders/MTLLoader.js';
import { OrbitControls } from '/main/three.js-master/examples/jsm/controls/OrbitControls.js';

let container;

let camera, scene, renderer;

let canvasX = window.innerWidth / 3;
let canvasY = window.innerHeight / 3;

let object;

init();
animate();

function init() {
    container = document.createElement('div');
    container.style.height = '100px';
    document.getElementById("pf-four").appendChild(container);

    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 2000);
    camera.position.z = 4;

    // scene
    scene = new THREE.Scene();
    scene.add(new THREE.AmbientLight(0xcccccc, 0.4));
    camera.add(new THREE.PointLight(0xffffff, 0.8));
    scene.add(camera);

    // manager
    function loadModel() {
        object.position.y = 1;
        object.rotateX(0.9);
        object.rotateY(4.7);
        object.rotateZ(0.4);
        scene.add(object);
    }

    const manager = new THREE.LoadingManager(loadModel);
    manager.onProgress = function (item, loaded, total) {
        console.log(item, loaded, total);
    };

    // model
    function onProgress(xhr) {
        if (xhr.lengthComputable) {
            const percentComplete = xhr.loaded / xhr.total * 100;
            console.log('model ' + Math.round(percentComplete, 2) + '% downloaded');
        }
    }

    function onError() {
    }

    const mtlLoader = new MTLLoader();
    const objLoader = new OBJLoader(manager);
    mtlLoader.load('../main/model/pf/pf_four.mtl', function (mtl) {
        mtl.preload();
        objLoader.setMaterials(mtl);
        objLoader.load('../main/model/pf/pf_four.obj', function (obj) {
            object = obj;
        }, onProgress, onError);
    });

    renderer = new THREE.WebGLRenderer({alpha: true});
    renderer.setPixelRatio(window.devicePixelRatio);
    // canvas大小
    renderer.setSize(canvasX, canvasY);
    container.appendChild(renderer.domElement);

    window.addEventListener('resize', onWindowResize);
}

function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(canvasX, canvasY);
}

function animate() {
    requestAnimationFrame(animate);
    render();
}

function render() {
    renderer.render(scene, camera);
}