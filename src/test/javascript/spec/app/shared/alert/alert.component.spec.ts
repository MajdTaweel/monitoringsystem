import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { JhiAlertService } from 'ng-jhipster';

import { MonitoringsystemTestModule } from '../../../test.module';
import { AlertComponent } from 'app/shared/alert/alert.component';

describe('Component Tests', () => {
  describe('Alert Component', () => {
    let comp: AlertComponent;
    let fixture: ComponentFixture<AlertComponent>;
    let alertService: JhiAlertService;

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        imports: [MonitoringsystemTestModule],
        declarations: [AlertComponent],
      })
        .overrideTemplate(AlertComponent, '')
        .compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(AlertComponent);
      comp = fixture.componentInstance;
      alertService = TestBed.get(JhiAlertService);
    });

    it('Should call alertService.get on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(alertService.get).toHaveBeenCalled();
    });

    it('Should call alertService.clear on destroy', () => {
      // WHEN
      comp.ngOnDestroy();

      // THEN
      expect(alertService.clear).toHaveBeenCalled();
    });
  });
});
