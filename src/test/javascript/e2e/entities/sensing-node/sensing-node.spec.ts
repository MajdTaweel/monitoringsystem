import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SensingNodeComponentsPage, SensingNodeDeleteDialog, SensingNodeUpdatePage } from './sensing-node.page-object';

const expect = chai.expect;

describe('SensingNode e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sensingNodeComponentsPage: SensingNodeComponentsPage;
  let sensingNodeUpdatePage: SensingNodeUpdatePage;
  let sensingNodeDeleteDialog: SensingNodeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SensingNodes', async () => {
    await navBarPage.goToEntity('sensing-node');
    sensingNodeComponentsPage = new SensingNodeComponentsPage();
    await browser.wait(ec.visibilityOf(sensingNodeComponentsPage.title), 5000);
    expect(await sensingNodeComponentsPage.getTitle()).to.eq('monitoringsystemApp.sensingNode.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(sensingNodeComponentsPage.entities), ec.visibilityOf(sensingNodeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SensingNode page', async () => {
    await sensingNodeComponentsPage.clickOnCreateButton();
    sensingNodeUpdatePage = new SensingNodeUpdatePage();
    expect(await sensingNodeUpdatePage.getPageTitle()).to.eq('monitoringsystemApp.sensingNode.home.createOrEditLabel');
    await sensingNodeUpdatePage.cancel();
  });

  it('should create and save SensingNodes', async () => {
    const nbButtonsBeforeCreate = await sensingNodeComponentsPage.countDeleteButtons();

    await sensingNodeComponentsPage.clickOnCreateButton();

    await promise.all([
      sensingNodeUpdatePage.setSnidInput('snid'),
      sensingNodeUpdatePage.sensingNodeTypeSelectLastOption(),
      sensingNodeUpdatePage.statusSelectLastOption(),
      sensingNodeUpdatePage.setBatteryInput('5'),
      sensingNodeUpdatePage.userSelectLastOption(),
    ]);

    expect(await sensingNodeUpdatePage.getSnidInput()).to.eq('snid', 'Expected Snid value to be equals to snid');
    expect(await sensingNodeUpdatePage.getBatteryInput()).to.eq('5', 'Expected battery value to be equals to 5');

    await sensingNodeUpdatePage.save();
    expect(await sensingNodeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sensingNodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SensingNode', async () => {
    const nbButtonsBeforeDelete = await sensingNodeComponentsPage.countDeleteButtons();
    await sensingNodeComponentsPage.clickOnLastDeleteButton();

    sensingNodeDeleteDialog = new SensingNodeDeleteDialog();
    expect(await sensingNodeDeleteDialog.getDialogTitle()).to.eq('monitoringsystemApp.sensingNode.delete.question');
    await sensingNodeDeleteDialog.clickOnConfirmButton();

    expect(await sensingNodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
