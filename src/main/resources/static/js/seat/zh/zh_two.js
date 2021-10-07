import * as THREE from '/js/three.js-master/build/three.module.js';

import {OBJLoader} from '/js/three.js-master/examples/jsm/loaders/OBJLoader.js';
import {MTLLoader} from '/js/three.js-master/examples/jsm/loaders/MTLLoader.js';
import {OrbitControls} from '/js/three.js-master/examples/jsm/controls/OrbitControls.js';

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
    container.style.textAlign = 'center';
    document.getElementById("zh-two").appendChild(container);

    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 2000);
    camera.position.z = 3;

    // scene
    scene = new THREE.Scene();
    scene.add(new THREE.AmbientLight(0xcccccc, 0.4));
    camera.add(new THREE.PointLight(0xffffff, 0.8));
    scene.add(camera);

    // manager
    function loadModel() {
        object.position.y = 0.5;
        object.rotateX(1);
        object.rotateY(4.7);
        object.rotateZ(0.6);
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
    mtlLoader.load('../model/zh/zh_two.mtl', function (mtl) {
        mtl.preload();
        objLoader.setMaterials(mtl);
        objLoader.load('../model/zh/zh_two.obj', function (obj) {
            object = obj;
        }, onProgress, onError);
    });

    renderer = new THREE.WebGLRenderer({alpha: true});
    renderer.setPixelRatio(window.devicePixelRatio);
    // canvas大小
    renderer.setSize(canvasX, canvasY);
    renderer.domElement.style.display = '';
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