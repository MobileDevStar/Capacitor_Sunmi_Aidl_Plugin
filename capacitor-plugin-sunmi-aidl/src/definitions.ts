declare module '@capacitor/core' {
  interface PluginRegistry {
    SunmiAidl: SunmiAidlPlugin;
  }
}

export interface SunmiAidlPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  openCashDrawer(): Promise<{status: string}>;
}
