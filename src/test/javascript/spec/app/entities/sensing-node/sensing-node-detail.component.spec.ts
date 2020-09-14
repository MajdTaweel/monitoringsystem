import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MonitoringsystemTestModule } from '../../../test.module';
import { SensingNodeDetailComponent } from 'app/entities/sensing-node/sensing-node-detail.component';
import { SensingNode } from 'app/shared/model/sensing-node.model';

describe('Component Tests', () => {
  describe('SensingNode Management Detail Component', () => {
    let comp: SensingNodeDetailComponent;
    let fixture: ComponentFixture<SensingNodeDetailComponent>;
    const route = ({ data: of({ sensingNode: new SensingNode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MonitoringsystemTestModule],
        declarations: [SensingNodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SensingNodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SensingNodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sensingNode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sensingNode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
