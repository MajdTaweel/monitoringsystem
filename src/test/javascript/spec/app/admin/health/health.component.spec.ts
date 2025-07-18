import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpErrorResponse } from '@angular/common/http';
import { of, throwError } from 'rxjs';

import { MonitoringsystemTestModule } from '../../../test.module';
import { HealthComponent } from 'app/admin/health/health.component';
import { HealthService, Health } from 'app/admin/health/health.service';

describe('Component Tests', () => {
  describe('HealthComponent', () => {
    let comp: HealthComponent;
    let fixture: ComponentFixture<HealthComponent>;
    let service: HealthService;

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        imports: [MonitoringsystemTestModule],
        declarations: [HealthComponent],
      })
        .overrideTemplate(HealthComponent, '')
        .compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(HealthComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HealthService);
    });

    describe('getBadgeClass', () => {
      it('should get badge class', () => {
        const upBadgeClass = comp.getBadgeClass('UP');
        const downBadgeClass = comp.getBadgeClass('DOWN');
        expect(upBadgeClass).toEqual('badge-success');
        expect(downBadgeClass).toEqual('badge-danger');
      });
    });

    describe('refresh', () => {
      it('should call refresh on init', () => {
        // GIVEN
        const health: Health = { status: 'UP', components: { mail: { status: 'UP', details: 'mailDetails' } } };
        spyOn(service, 'checkHealth').and.returnValue(of(health));

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(service.checkHealth).toHaveBeenCalled();
        expect(comp.health).toEqual(health);
      });

      it('should handle a 503 on refreshing health data', () => {
        // GIVEN
        const health: Health = { status: 'DOWN', components: { mail: { status: 'DOWN', details: 'mailDetails' } } };
        spyOn(service, 'checkHealth').and.returnValue(throwError(new HttpErrorResponse({ status: 503, error: health })));

        // WHEN
        comp.refresh();

        // THEN
        expect(service.checkHealth).toHaveBeenCalled();
        expect(comp.health).toEqual(health);
      });
    });
  });
});
