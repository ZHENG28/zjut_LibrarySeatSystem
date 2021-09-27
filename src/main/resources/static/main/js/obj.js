import * as THREE from '../three.js-master/build/three.module.js';

import {OBJLoader} from '../three.js-master/examples/jsm/loaders/OBJLoader.js';

let container;

let camera, scene, renderer;

let mouseX = 0, mouseY = 0;

let windowHalfX = window.innerWidth / 2;
let windowHalfY = window.innerHeight / 2;

let canvasX = window.innerWidth / 2;
let canvasY = window.innerHeight / 2;

let object;

init();
animate();


function init() {

    container = document.createElement('div');
    document.getElementById("outmap").appendChild(container);

    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 2000);
    camera.position.z = 5;

    // scene
    scene = new THREE.Scene();

    scene.add(new THREE.AmbientLight(0xcccccc, 0.4));
    camera.add(new THREE.PointLight(0xffffff, 0.8));
    scene.add(camera);

    // manager
    function loadModel() {
        object.traverse(function (child) {
            // if (child.isMesh) child.material.map = texture;
        });

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

    const loader = new OBJLoader(manager);
    loader.load('main/model/pf.obj', function (obj) {
        object = obj;
    }, onProgress, onError);

    renderer = new THREE.WebGLRenderer();
    renderer.setPixelRatio(window.devicePixelRatio);
    // canvas大小
    renderer.setSize(canvasX, canvasY);
    container.appendChild(renderer.domElement);

    document.addEventListener('mousemove', onDocumentMouseMove);

    window.addEventListener('resize', onWindowResize);
}

function onWindowResize() {
    windowHalfX = window.innerWidth / 2;
    windowHalfY = window.innerHeight / 2;

    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(canvasX, canvasY);
}

function onDocumentMouseMove(event) {
    if ((event.clientX <= canvasX && event.clientX >= 0) && (event.clientY <= canvasY && event.clientY >= 0)) {
        mouseX = (event.clientX - windowHalfX) / 150;
        mouseY = (event.clientY - windowHalfY) / 150;
    }
}

//

function animate() {
    requestAnimationFrame(animate);
    // 设置物体自动旋转
    object.rotation.y += 0.01;
    render();
}

function render() {
    camera.position.x += (mouseX - camera.position.x) * .05;
    camera.position.y += (-mouseY - camera.position.y) * .05;

    camera.lookAt(scene.position);

    renderer.render(scene, camera);

}