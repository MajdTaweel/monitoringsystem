import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MonitoringsystemTestModule } from '../../../test.module';
import { PollutionReadingsComponent } from 'app/entities/pollution-readings/pollution-readings.component';
import { PollutionReadingsService } from 'app/entities/pollution-readings/pollution-readings.service';
import { PollutionReadings } from 'app/shared/model/pollution-readings.model';

describe('Component Tests', () => {
  describe('PollutionReadings Management Component', () => {
    let comp: PollutionReadingsComponent;
    let fixture: ComponentFixture<PollutionReadingsComponent>;
    let service: PollutionReadingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MonitoringsystemTestModule],
        declarations: [PollutionReadingsComponent],
      })
        .overrideTemplate(PollutionReadingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PollutionReadingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PollutionReadingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PollutionReadings(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pollutionReadings && comp.pollutionReadings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
