import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MonitoringsystemTestModule } from '../../../test.module';
import { PollutionReadingsDetailComponent } from 'app/entities/pollution-readings/pollution-readings-detail.component';
import { PollutionReadings } from 'app/shared/model/pollution-readings.model';

describe('Component Tests', () => {
  describe('PollutionReadings Management Detail Component', () => {
    let comp: PollutionReadingsDetailComponent;
    let fixture: ComponentFixture<PollutionReadingsDetailComponent>;
    const route = ({ data: of({ pollutionReadings: new PollutionReadings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MonitoringsystemTestModule],
        declarations: [PollutionReadingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PollutionReadingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PollutionReadingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pollutionReadings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pollutionReadings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
