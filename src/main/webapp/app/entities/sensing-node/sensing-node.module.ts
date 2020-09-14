import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MonitoringsystemSharedModule } from 'app/shared/shared.module';
import { SensingNodeComponent } from './sensing-node.component';
import { SensingNodeDetailComponent } from './sensing-node-detail.component';
import { SensingNodeUpdateComponent } from './sensing-node-update.component';
import { SensingNodeDeleteDialogComponent } from './sensing-node-delete-dialog.component';
import { sensingNodeRoute } from './sensing-node.route';

@NgModule({
  imports: [MonitoringsystemSharedModule, RouterModule.forChild(sensingNodeRoute)],
  declarations: [SensingNodeComponent, SensingNodeDetailComponent, SensingNodeUpdateComponent, SensingNodeDeleteDialogComponent],
  entryComponents: [SensingNodeDeleteDialogComponent],
})
export class MonitoringsystemSensingNodeModule {}
