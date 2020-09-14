import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'sensing-node',
        loadChildren: () => import('./sensing-node/sensing-node.module').then(m => m.MonitoringsystemSensingNodeModule),
      },
      {
        path: 'magnetometer-readings',
        loadChildren: () =>
          import('./magnetometer-readings/magnetometer-readings.module').then(m => m.MonitoringsystemMagnetometerReadingsModule),
      },
      {
        path: 'pollution-readings',
        loadChildren: () => import('./pollution-readings/pollution-readings.module').then(m => m.MonitoringsystemPollutionReadingsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MonitoringsystemEntityModule {}
