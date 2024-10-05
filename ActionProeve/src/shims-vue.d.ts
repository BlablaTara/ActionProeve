
//Dette ved jeg heller ikke hvad betyder.
//Chat siger at det er fordi at vue's komponenter automatisk bliver defineret
// som type:any og det kan typescript ikke lide.
declare module '*.vue' {
    import { DefineComponent } from 'vue';
    const component: DefineComponent<{}, {}, any>;
    export default component;
}