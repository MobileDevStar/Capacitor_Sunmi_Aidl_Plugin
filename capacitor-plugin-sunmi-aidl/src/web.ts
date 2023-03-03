import { WebPlugin } from '@capacitor/core';
import { SunmiAidlPlugin } from './definitions';

export class SunmiAidlWeb extends WebPlugin implements SunmiAidlPlugin {
  constructor() {
    super({
      name: 'SunmiAidl',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async openCashDrawer(): Promise<{ status: string }> {
    return {
      status: 'OK'
    };
  }
}

const SunmiAidl = new SunmiAidlWeb();

export { SunmiAidl };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(SunmiAidl);
