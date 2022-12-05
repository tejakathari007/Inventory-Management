import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnInit,
  ViewChild,
} from "@angular/core";
import * as THREE from "three";

@Component({
  selector: "ngx-arvr",
  templateUrl: "./arvr.component.html",
  styleUrls: ["./arvr.component.scss"],
})
export class ArvrComponent implements OnInit, AfterViewInit {
  @ViewChild("canvas") private canvasRef: ElementRef;
  constructor() {}
  @Input() public rotationSpeedX: number = 0.01;
  @Input() public rotationSpeedY: number = 0.01;

  @Input() public size: number = 200;
  @Input() public texture: string = 'assets/images/server1.gif';
  @Input() public bgtexture: string = 'assets/images/bg1.jpg';
  @Input() public cameraZ: number = 400;

  @Input() public fieldOfview: number = 1;

  @Input("nearClipping") public nearClippingPlane: number = 1;

  @Input("farClipping") public farClippingPlane: number = 1000;

  private camera!: THREE.PerspectiveCamera;

  private get canvas(): HTMLCanvasElement {
    return this.canvasRef.nativeElement;
  }

  private loader = new THREE.TextureLoader();
  //ageometry = new THREE.TubeGeometry( apath, 20, 2, 8, false ); 
  private geometry = new THREE.CylinderGeometry(0.5,0.5,4,4,10,false,24 , 22);
  private rgeometry = new THREE.CylinderGeometry(0.5,0.5,4,4,10,false,10 , 12);
  private material = new THREE.MeshBasicMaterial({
   // map: this.loader.load(this.texture),
    color: 0xFF8001
  });
  private cube: THREE.Mesh = new THREE.Mesh(this.geometry, this.material);
  private cube1: THREE.Mesh = new THREE.Mesh(this.rgeometry, this.material);
  private cube2: THREE.Mesh = new THREE.Mesh(this.geometry, this.material);
  private renderer!: THREE.WebGLRenderer;
  private scene!: THREE.Scene;
  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.createScene();
    this.startRenderingLoop();
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
  }

  private createScene() {
    //* scene
    this.scene = new THREE.Scene();
    this.scene.background = this.loader.load(this.bgtexture);
   
    //* Camera
    let aspectRation = this.getAspectRation();
    this.camera = new THREE.PerspectiveCamera(
      this.fieldOfview,
      aspectRation,
      this.nearClippingPlane,
      this.farClippingPlane
    );
    this.camera.position.z = this.cameraZ;
  }
  private getAspectRation() {
    return this.canvas.clientWidth / this.canvas.clientHeight;
  }

  private animateCube() {
    // this.cube.rotation.x += this.rotationSpeedX;
    this.cube.rotation.y += this.rotationSpeedY;
  }
  startRenderingLoop() {
      this.renderer = new THREE.WebGLRenderer({canvas:this.canvas});
      this.renderer.setPixelRatio(devicePixelRatio);
      this.renderer.setSize(this.canvas.clientWidth,this.canvas.clientHeight);
      this.scene.add(this.cube);
      this.scene.add(this.cube1);
      // this.scene.add(this.cube2);
      this.renderer.setAnimationLoop(() => {
      this.animateCube();
      this.renderer.render(this.scene, this.camera);
      });
  }
}



