import * as THREE from '/main/three.js-master/build/three.module.js';

THREE.Cache.enabled = true;

let container;

let camera, cameraTarget, scene, renderer;

let group, textMesh1, textMesh2, textGeo, materials;

let text = "浙江工业大学\n图书馆选座系统",

    bevelEnabled = true,

    font = undefined;

const height = 15,
    size = 50,
    hover = 80,

    curveSegments = 4,

    bevelThickness = 2,
    bevelSize = 1.5;

const mirror = true;

let targetRotation = 0;
let targetRotationOnPointerDown = 0;

let pointerX = 0;
let pointerXOnPointerDown = 0;

let windowHalfX = window.innerWidth / 2;

init();
animate();

function init() {
    container = document.createElement('div');
    container.style.height = '450px';
    document.getElementById("welcome-container").appendChild(container);

    // CAMERA
    camera = new THREE.PerspectiveCamera(30, window.innerWidth / window.innerHeight, 1, 1500);
    camera.position.set(0, 400, 700);

    cameraTarget = new THREE.Vector3(0, 50, 0);

    // SCENE
    scene = new THREE.Scene();
    scene.background = new THREE.Color(0xffffff);
    scene.fog = new THREE.Fog(0xffffff, 250, 1400);

    // LIGHTS
    const dirLight = new THREE.DirectionalLight(0xffffff, 0.125);
    dirLight.position.set(0, 0, 1).normalize();
    scene.add(dirLight);

    const pointLight = new THREE.PointLight(0xffffff, 1.5);
    pointLight.position.set(0, 100, 90);
    scene.add(pointLight);

    // pointLight.color.setHSL(Math.random(), 1, 0.5);
    pointLight.color.setHSL(0.55, 1, 0.5);

    materials = [
        new THREE.MeshPhongMaterial({color: 0xffffff, flatShading: true}), // front
        new THREE.MeshPhongMaterial({color: 0xffffff}) // side
    ];

    group = new THREE.Group();
    group.position.y = 100;
    scene.add(group);

    loadFont();

    const plane = new THREE.Mesh(
        new THREE.PlaneGeometry(10000, 10000),
        new THREE.MeshBasicMaterial({color: 0xffffff, opacity: 0.5, transparent: true})
    );
    plane.position.y = 100;
    plane.rotation.x = -Math.PI / 2;
    scene.add(plane);

    // RENDERER
    renderer = new THREE.WebGLRenderer({antialias: true});
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    container.appendChild(renderer.domElement);

    // STATS
    // stats = new Stats();
    //container.appendChild( stats.dom );

    // EVENTS
    container.style.touchAction = 'none';
    container.addEventListener('pointerdown', onPointerDown);

    window.addEventListener('resize', onWindowResize);

}

function onWindowResize() {
    windowHalfX = window.innerWidth / 2;

    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(window.innerWidth, window.innerHeight);
}

function loadFont() {
    const loader = new THREE.FontLoader();
    loader.load('/three.js-master/examples/fonts/KaiTi_regular.json', function (response) {
        font = response;
        refreshText();
    });
}

function createText() {
    // 中文存在bug
    textGeo = new THREE.TextGeometry(text, {
        font: font,

        size: size,
        height: height,
        curveSegments: curveSegments,

        bevelThickness: bevelThickness,
        bevelSize: bevelSize,
        bevelEnabled: bevelEnabled
    });

    textGeo.computeBoundingBox();

    const centerOffset = -0.5 * (textGeo.boundingBox.max.x - textGeo.boundingBox.min.x);


    textMesh1 = new THREE.Mesh(textGeo, materials);

    textMesh1.position.x = centerOffset;
    textMesh1.position.y = hover;
    textMesh1.position.z = 0;

    textMesh1.rotation.x = 0;
    textMesh1.rotation.y = Math.PI * 2;

    group.add(textMesh1);


    if (mirror) {

        textMesh2 = new THREE.Mesh(textGeo, materials);

        textMesh2.position.x = centerOffset;
        textMesh2.position.y = -hover;
        textMesh2.position.z = height;

        textMesh2.rotation.x = Math.PI;
        textMesh2.rotation.y = Math.PI * 2;

        group.add(textMesh2);

    }
}

function refreshText() {
    group.remove(textMesh1);
    if (mirror) group.remove(textMesh2);

    if (!text) return;

    createText();
}

function onPointerDown(event) {
    if (event.isPrimary === false) return;

    pointerXOnPointerDown = event.clientX - windowHalfX;
    targetRotationOnPointerDown = targetRotation;

    document.addEventListener('pointermove', onPointerMove);
    document.addEventListener('pointerup', onPointerUp);
}

function onPointerMove(event) {
    if (event.isPrimary === false) return;

    pointerX = event.clientX - windowHalfX;

    targetRotation = targetRotationOnPointerDown + (pointerX - pointerXOnPointerDown) * 0.02;
}

function onPointerUp() {
    if (event.isPrimary === false) return;

    document.removeEventListener('pointermove', onPointerMove);
    document.removeEventListener('pointerup', onPointerUp);
}

//

function animate() {
    requestAnimationFrame(animate);

    render();
    // stats.update();
}

function render() {
    group.rotation.y += (targetRotation - group.rotation.y) * 0.05;

    camera.lookAt(cameraTarget);

    renderer.clear();
    renderer.render(scene, camera);
}