import { VitalSignsAppPage } from './app.po';

describe('vitalsigns-app App', () => {
  let page: VitalSignsAppPage;

  beforeEach(() => {
    page = new VitalSignsAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
